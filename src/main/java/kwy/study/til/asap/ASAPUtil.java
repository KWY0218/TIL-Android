package kwy.study.til.asap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kwy.study.til.asap.domain.DateAvailability;
import kwy.study.til.asap.domain.Duration;
import kwy.study.til.asap.domain.Meeting;
import kwy.study.til.asap.domain.MeetingTime;
import kwy.study.til.asap.domain.Result;
import kwy.study.til.asap.domain.TimeSlot;
import kwy.study.til.asap.domain.TimeSlotInfo;
import lombok.Getter;

@Getter
public class ASAPUtil {
    private Map<String, Map<TimeSlot, TimeSlotInfo>> timeTable = new HashMap<>();
    private final Meeting meeting = Meeting.getDummy();
    private final List<MeetingTime> meetingTimes = MeetingTime.getDummy();
    private final Map<Duration, List<Result>> results = new HashMap<>();

    public void initTimeTable() {
        TimeSlot[] timeSlots = TimeSlot.values();
        Duration[] durations = Duration.values();

        for (DateAvailability ad : meeting.getDateAvailabilities()) {
            Map<TimeSlot, TimeSlotInfo> rowTable = new HashMap<>();

            for (TimeSlot t : timeSlots) {
                rowTable.put(t, new TimeSlotInfo());
            }

            String col = String.format("%s.%s.%s", ad.getMonth(), ad.getDay(), ad.getDayOfWeek());
            timeTable.put(col, rowTable);
        }

        for (Duration d : durations) {
            results.put(d, new ArrayList<>());
        }
    }

    public void setTimeTable() {
        for (MeetingTime mt : meetingTimes) {
            String col = String.format("%s.%s.%s", mt.getMonth(), mt.getDay(), mt.getDayOfWeek());
            List<TimeSlot> timeSlots = TimeSlot.getTimeSlots(mt.getStartTime().ordinal(), mt.getEndTime().ordinal());
            Map<TimeSlot, TimeSlotInfo> rowTable = timeTable.get(col);

            for (TimeSlot ts : timeSlots) {
                TimeSlotInfo timeSlotInfo = rowTable.get(ts);
                timeSlotInfo.userNames.add(mt.getUserName());
                timeSlotInfo.weight += mt.getPriority();
            }
        }
    }

    public void getFinalDate() {
        Duration[] durations = Duration.values();
        for (int i = meeting.getDuration().ordinal(); i >= 0; i--) {
            List<Result> resultList = results.get(durations[i]);
            if (!resultList.isEmpty()) {
                Collections.sort(resultList);
            }
        }
    }

    public void total() {
        Duration[] durations = Duration.values();
        for (Duration d : durations) {
            collectPossibleTime(d);
        }
    }

    public void collectPossibleTime(Duration duration) {
        int needBlock = duration.getNeedBlock();
        Set<String> colNames = timeTable.keySet();

        for (String col : colNames) {
            Map<TimeSlot, TimeSlotInfo> timeSlotInfos = timeTable.get(col);
            TimeSlot[] timeSlots = TimeSlot.values();

            for (int i = 0; i < timeSlots.length - needBlock + 1; i++) {
                List<String> userNames = timeSlotInfos.get(timeSlots[i]).userNames;
                if (!userNames.isEmpty()) {

                    List<String> resultUserNames = new ArrayList<>();

                    for (String userName : userNames) {
                        int count = 1;

                        for (int block = i + 1; block < i + needBlock; block++) {

                            List<String> nextUserNames = timeSlotInfos.get(timeSlots[block]).userNames;
                            if (!nextUserNames.contains(userName)) break;
                            count++;

                        }

                        if (count == needBlock) {
                            resultUserNames.add(userName);
                        }

                    }

                    if (!resultUserNames.isEmpty()) {

                        TimeSlot startTime = timeSlots[i];
                        TimeSlot endTime = timeSlots[i + needBlock - 1];

                        int weight = 0;
                        for (int block = i + 1; block < i + needBlock; block++) {
                            weight += timeSlotInfos.get(timeSlots[block]).weight;
                        }

                        Result result = new Result(col, startTime, endTime, weight / (needBlock - 1), resultUserNames);
                        results.get(duration).add(result);

                    }

                }
            }
        }
    }

}

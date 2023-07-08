package kwy.study.til.asap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kwy.study.til.asap.domain.DateAvailability;
import kwy.study.til.asap.domain.Meeting;
import kwy.study.til.asap.domain.MeetingTime;
import kwy.study.til.asap.domain.PreferTime;
import kwy.study.til.asap.domain.TimeSlot;
import kwy.study.til.asap.domain.TimeSlotInfo;
import lombok.Getter;

@Getter
public class ASAPUtil {
    private Map<String, Map<TimeSlot, TimeSlotInfo>> timeTable = new HashMap<>();
    private final Meeting meeting = Meeting.getDummy();
    private final List<MeetingTime> meetingTimes = MeetingTime.getDummy();

    public void initTimeTable() {
        List<PreferTime> pts = meeting.getPreferTimes();
        List<TimeSlot> timeSlots = TimeSlot.getTimeSlots(pts.get(0).getStartTime().ordinal(), pts.get(0).getEndTime().ordinal());
        for (DateAvailability ad : meeting.getDateAvailabilities()) {
            Map<TimeSlot, TimeSlotInfo> rowTable = new HashMap<>();
            for (TimeSlot t : timeSlots) {
                rowTable.put(t, new TimeSlotInfo());
            }
            String col = String.format("%s.%s.%s", ad.getMonth(), ad.getDay(), ad.getDayOfWeek());
            timeTable.put(col, rowTable);
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
}

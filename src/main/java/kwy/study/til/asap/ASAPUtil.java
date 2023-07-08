package kwy.study.til.asap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kwy.study.til.asap.domain.DateAvailability;
import kwy.study.til.asap.domain.Meeting;
import kwy.study.til.asap.domain.PreferTime;
import kwy.study.til.asap.domain.TimeSlot;
import lombok.Getter;

@Getter
public class ASAPUtil {
    private Map<String, Map<TimeSlot, List<String>>> timeTable = new HashMap<>();
    private final Meeting meeting = Meeting.getDummy();

    public void setTimeTable() {
        List<PreferTime> pts = meeting.getPreferTimes();
        List<TimeSlot> timeSlots = TimeSlot.getTimeSlots(pts.get(0).getStartTime().ordinal(), pts.get(0).getEndTime().ordinal());
        for (DateAvailability ad : meeting.getDateAvailabilities()) {
            Map<TimeSlot, List<String>> rowTable = new HashMap<>();
            for (TimeSlot t : timeSlots) {
                rowTable.put(t, new ArrayList<>());
            }
            String col = String.format("%s.%s.%s", ad.getMonth(), ad.getDay(), ad.getDayOfWeek());
            timeTable.put(col, rowTable);
        }
    }

}

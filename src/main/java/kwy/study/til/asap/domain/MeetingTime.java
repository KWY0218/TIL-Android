package kwy.study.til.asap.domain;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeetingTime {
    private TimeSlot startTime;
    private TimeSlot endTime;
    private String userName;
    private int priority;
    private String month;
    private String day;
    private String dayOfWeek;

    /*
    *  7.1.토 22~24 -> 4명 [원용, 소현, 지원, 채원]
    *  7.2.일 18~20 -> 3명 [원용, 소현, 지원]
    *  7.2.일 22~24 -> 1명 [채원]
    *  7.3.월 18~20 -> 2명 [원용, 소현]
    *  7.4.화 18~20 -> 2명 [지원, 채원]
    * */
    public static List<MeetingTime> getDummy() {
        MeetingTime t1 = new MeetingTime(TimeSlot.SLOT_22_00, TimeSlot.SLOT_24_00, "강원용", 0, "7", "1", "토");
        MeetingTime t2 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "강원용", 0, "7", "2", "일");
        MeetingTime t3 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "강원용", 0, "7", "3", "월");

        MeetingTime t4 = new MeetingTime(TimeSlot.SLOT_22_00, TimeSlot.SLOT_24_00, "도소현", 0, "7", "1", "토");
        MeetingTime t5 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "도소현", 0, "7", "2", "일");
        MeetingTime t6 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "도소현", 0, "7", "3", "월");

        MeetingTime t7 = new MeetingTime(TimeSlot.SLOT_22_00, TimeSlot.SLOT_24_00, "서지원", 0, "7", "1", "토");
        MeetingTime t8 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "서지원", 0, "7", "2", "일");
        MeetingTime t9 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "서지원", 2, "7", "4", "화");

        MeetingTime t10 = new MeetingTime(TimeSlot.SLOT_22_00, TimeSlot.SLOT_24_00, "서채원", 0, "7", "1", "토");
        MeetingTime t11 = new MeetingTime(TimeSlot.SLOT_22_00, TimeSlot.SLOT_24_00, "서채원", 1, "7", "2", "일");
        MeetingTime t12 = new MeetingTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_20_00, "서채원", 1, "7", "4", "화");
        return Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);
    }
}

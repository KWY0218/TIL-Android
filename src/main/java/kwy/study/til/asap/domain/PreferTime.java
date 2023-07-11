package kwy.study.til.asap.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PreferTime {
    private TimeSlot startTime;
    private TimeSlot endTime;

    public static List<PreferTime> getDummy() {
        PreferTime p = new PreferTime(TimeSlot.SLOT_18_00, TimeSlot.SLOT_24_00);
        return List.of(p);
    }
}

package kwy.study.til.asap.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeetingTime {
    private TimeSlot startTime;
    private TimeSlot endTime;
    private int priority;
}

package kwy.study.til.asap.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Result {
    private String date;
    private TimeSlot startTime;
    private TimeSlot endTime;
    private int weight;
    private List<String> userNames;
}

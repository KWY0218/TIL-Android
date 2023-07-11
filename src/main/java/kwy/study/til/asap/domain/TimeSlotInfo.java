package kwy.study.til.asap.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TimeSlotInfo {
    public List<String> userNames = new ArrayList<>();
    public int weight = 0;
}

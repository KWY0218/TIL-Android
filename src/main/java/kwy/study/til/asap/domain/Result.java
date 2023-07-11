package kwy.study.til.asap.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Result implements Comparable<Result> {
    private String date;
    private TimeSlot startTime;
    private TimeSlot endTime;
    private int weight;
    private List<String> userNames;

    @Override
    public int compareTo(Result o) {
        if (this.userNames.size() == o.userNames.size()) {
            return Integer.compare(o.weight, this.weight);
        }
        return Integer.compare(o.userNames.size(), this.userNames.size());
    }
}

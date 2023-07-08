package kwy.study.til.asap.domain;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DateAvailability {
    private String month;
    private String day;
    private String dayOfWeek;

    public static List<DateAvailability> getDummy() {
        DateAvailability d1 = new DateAvailability("7", "1", "토");
        DateAvailability d2 = new DateAvailability("7", "2", "일");
        DateAvailability d3 = new DateAvailability("7", "3", "월");
        DateAvailability d4 = new DateAvailability("7", "4", "화");
        return Arrays.asList(d1, d2, d3, d4);
    }
}

package kwy.study.til.asap.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Meeting {
    private List<User> users;
    private List<DateAvailability> dateAvailabilities;
    private List<PreferTime> preferTimes;
    private String duration;
}

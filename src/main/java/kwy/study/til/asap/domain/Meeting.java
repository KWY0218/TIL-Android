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

    public static Meeting getDummy() {
        List<User> users = User.getDummy();
        List<DateAvailability> dateAvailabilities = DateAvailability.getDummy();
        List<PreferTime> preferTimes = PreferTime.getDummy();
        String duration = "2HOUR";

        return new Meeting(users, dateAvailabilities, preferTimes, duration);
    }
}

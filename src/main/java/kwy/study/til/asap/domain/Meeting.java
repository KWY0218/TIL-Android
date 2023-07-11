package kwy.study.til.asap.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static kwy.study.til.asap.domain.Duration.TWO_HOUR;

@AllArgsConstructor
@Getter
public class Meeting {
    private List<User> users;
    private List<DateAvailability> dateAvailabilities;
    private List<PreferTime> preferTimes;
    private Duration duration;

    public static Meeting getDummy() {
        List<User> users = User.getDummy();
        List<DateAvailability> dateAvailabilities = DateAvailability.getDummy();
        List<PreferTime> preferTimes = PreferTime.getDummy();

        return new Meeting(users, dateAvailabilities, preferTimes, TWO_HOUR);
    }
}

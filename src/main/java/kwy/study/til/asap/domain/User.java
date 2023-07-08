package kwy.study.til.asap.domain;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private String name;

    public static List<User> getDummy() {
        User u1 = new User("강원용");
        User u2 = new User("도소현");
        User u3 = new User("서지원");
        User u4 = new User("서채원");
        return Arrays.asList(u1, u2, u3, u4);
    }
}

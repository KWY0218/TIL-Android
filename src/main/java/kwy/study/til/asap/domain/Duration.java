package kwy.study.til.asap.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Duration {
    HALF(2),
    ONE_HOUR(3),
    ONE_HOUR_HALF(4),
    TWO_HOUR(5),
    TWH_HOUR_HALF(6),
    THREE_HOUR(7);

    private int needBlock;
}

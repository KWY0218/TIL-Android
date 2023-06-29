package kwy.study.til.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private int status;
    private String message;

    public static ErrorResponse error() {
        return new ErrorResponse(400, "errorCode.getMessage()");
    }
}
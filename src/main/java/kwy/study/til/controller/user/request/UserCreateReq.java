package kwy.study.til.controller.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "유저 생성 DTO")
public class UserCreateReq {
    @Schema(description = "유저 이름", example = "강원용")
    private String name;
}

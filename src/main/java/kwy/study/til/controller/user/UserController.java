package kwy.study.til.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kwy.study.til.common.ErrorResponse;
import kwy.study.til.controller.user.request.UserCreateReq;
import kwy.study.til.controller.user.response.UserResponse;
import kwy.study.til.domain.User;
import kwy.study.til.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User API", description = "유저 API Docs")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "유저 생성 API",
            description = "유저를 서버에 생성합니다."
    )
    public UserResponse createUser(@RequestBody final UserCreateReq body) {
        User user = userService.createUser(body.getName());
        return new UserResponse(user.getId(), user.getName());
    }

    @PutMapping("/{userId}")
    @Operation(summary = "유저 update API", description = "유저 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 유저 정보가 없습니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public UserResponse updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody final UserCreateReq body
    ) {
        User user = userService.updateUser(userId, body.getName());
        return new UserResponse(user.getId(), user.getName());
    }

    @GetMapping("/{userId}")
    @Operation(
            summary = "유저 get API",
            description = "유저 정보 하나를 불러옵니다."
    )
    @Parameters({
            @Parameter(name = "userId", description = "조회 할 user 의 id", required = true)
    })
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "유저 delete API", description = "유저 정보를 삭제합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "조회 할 user 의 id", required = true)
    })
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}

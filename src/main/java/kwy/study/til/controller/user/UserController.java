package kwy.study.til.controller.user;

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
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody final UserCreateReq body) {
        User user = userService.createUser(body.getName());
        return new UserResponse(user.getId(), user.getName());
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody final UserCreateReq body
    ) {
        User user = userService.updateUser(userId, body.getName());
        return new UserResponse(user.getId(), user.getName());
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}

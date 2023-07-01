package kwy.study.til.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "hello world";
    }

    @GetMapping("/hello2")
    public String getHello2() {
        return "hello world222";
    }
}

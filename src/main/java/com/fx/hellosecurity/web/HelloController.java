package com.fx.hellosecurity.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloController {

    @GetMapping("/login")
    public String hello() {
        return "hello";
    }
}

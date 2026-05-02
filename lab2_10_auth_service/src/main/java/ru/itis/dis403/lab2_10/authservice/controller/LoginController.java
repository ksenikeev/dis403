package ru.itis.dis403.lab2_10.authservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/api/v1/")
public class LoginController {

    @GetMapping("/auth/login")
    public String login(@RequestParam String key) {

    }

}

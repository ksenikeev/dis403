package ru.itis.dis403.lab2_6.controller.gui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppPage {

    @GetMapping("/app")
    public String appPage() {
        return "app";
    }

}

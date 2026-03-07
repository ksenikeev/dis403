package ru.itis.dis403.lab2_4.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dis403.lab2_4.model.Phone;
import ru.itis.dis403.lab2_4.service.PhoneService;

import java.util.List;

@Controller
public class IndexController {

    private final PhoneService phoneService;

    public IndexController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Phone> phones = phoneService.findAll();

        model.addAttribute("phones", phones);

        return "index";
    }

}

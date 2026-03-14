package ru.itis.dis403.lab2_5.controller;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dis403.lab2_5.security.UserDetailImpl;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        //UserDetailImpl userDetails2 = (UserDetailImpl)authentication.getDetails();

        model.addAttribute("user", userDetails.getUsername());
        return "index";
    }
}

package ru.itis.dis403.lab2_5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dis403.lab2_5.security.UserDetailImpl;

@Controller
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/index")
    public String index(Model model) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();


        SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().forEach(System.out::println);
        model.addAttribute("user", userDetails.getUsername());


        return "adminindex";
    }

}

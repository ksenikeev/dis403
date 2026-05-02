package ru.itis.dis403.lab2_10.authservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.itis.dis403.lab2_10.authservice.clientsapp.AppData;
import ru.itis.dis403.lab2_10.authservice.service.ApiClientService;
import ru.itis.dis403.lab2_10.authservice.service.UserService;

@Controller("/api/v1/")
public class LoginController {

    private final ApiClientService service;
    private final UserService userService;

    public LoginController(ApiClientService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam("response_type") String responseType,
                        @RequestParam("client_id") String clientId, Model model) {
        AppData appData = service.getApp(clientId);

        model.addAttribute("grants", appData.getScopes());
        model.addAttribute("clientId", clientId);

        return "login";
    }

    @PostMapping("/auth/login")
    public RedirectView authentificate(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("clientId") String clientId) {


        AppData appData = service.getApp(clientId);
        RedirectView rv = new RedirectView();
        rv.setUrl("http://");
        return rv;
    }

}

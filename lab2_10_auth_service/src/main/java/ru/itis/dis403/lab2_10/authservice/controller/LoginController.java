package ru.itis.dis403.lab2_10.authservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.itis.dis403.lab2_10.authservice.clientsapp.AppData;
import ru.itis.dis403.lab2_10.authservice.model.User;
import ru.itis.dis403.lab2_10.authservice.service.ApiClientService;
import ru.itis.dis403.lab2_10.authservice.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/")
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
        System.out.println(appData.getAppRedirectURL());
        return "login";
    }

    @PostMapping("/auth/login")
    public RedirectView authentificate(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("clientId") String clientId) {

        User user = userService.findByUsername(username);

        RedirectView rv = new RedirectView();
        AppData appData = service.getApp(clientId);

        if (user != null && user.getPassword().equals(password)) {
            String code = UUID.randomUUID().toString();
            userService.getCodeForInnerApps().put(code, user.getId());
            // формируем редирект на клиентское приложение + код пользователя для jwt токена
            rv.setUrl(appData.getAppRedirectURL() + "?code=" + code);
        } else {
            // TODO проверить без кодирования пробела
            rv.setUrl(appData.getAppRedirectURL() + "?error=Authentification Error");
        }
        return rv;
    }
}

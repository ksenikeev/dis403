package ru.itis.dis403.lab2_10.authservice.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dis403.lab2_10.authservice.clientsapp.AppData;
import ru.itis.dis403.lab2_10.authservice.model.User;
import ru.itis.dis403.lab2_10.authservice.service.ApiClientService;
import ru.itis.dis403.lab2_10.authservice.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Controller
@RequestMapping("/api/v1/")
public class ResourceController {

    private final UserService userService;
    private final ApiClientService apiClientService;

    public ResourceController(UserService userService, ApiClientService apiClientService) {
        this.userService = userService;
        this.apiClientService = apiClientService;
    }

    /**
     * Выдача токена доступа к ресурсам взамен кода
     * @param code
     * @return
     */
    @GetMapping("/jwt")
    public ResponseEntity<String> getResourceJwt(@RequestParam("code") String code,
                                     @RequestParam("appId") String appId) {
        Long userId = userService.getCodeForInnerApps().get(code);
        AppData appData = apiClientService.getApp(appId);

        if (userId != null) {
            userService.getCodeForInnerApps().remove(code);

            User user = userService.findById(userId);

            return ResponseEntity.ok(generateToken(user, appData));
        }

        return ResponseEntity.ok(null);
    }

    @GetMapping("/resources")
    public ResponseEntity<String> getResources(@RequestParam("jwt") String jwt) {
        // 1. Проверка валидности токена
        // 2. Получение пользователя
        // 3. Определение scope - информации которую надо вернуть
        // 4. Получаем и возвращаем информацию
        return ResponseEntity.ok("{}");
    }



    public String generateToken(User user, AppData appData) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("scope", String.join(",", appData.getScopes()));
        return Jwts.builder()
                .claims(claims)
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*3))
                .signWith(Keys.hmacShaKeyFor("1234".getBytes()))
                .compact();
    }

}

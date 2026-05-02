package ru.itis.dis403.lab2_10.authservice.clientsapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Данные на зарегистрированные в сервисе авторизации приложения
 */
@Getter@Setter@AllArgsConstructor
public class AppData {
    private String appId; // Идентификатор нашего приложения
    private String appKey; // Секретный ключ
    private String appRedirectURL; // URL на который будет произведен редирект после
    //успешной аутентификации пользователя
    private List<String> scopes; // ресурсы нашего пользователя
}

package ru.itis.dis403.lab2_10.authservice.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_10.authservice.clientsapp.AppData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiClientService {

    private Map<String, AppData> apps;

    public ApiClientService() {
        apps = new HashMap<>();
        // "регистрируем" наше приложение
        apps.put("app123456",
                new AppData("app123456", "app123456",
                        "http://localhost:8095/redirectauth",
                List.of(new String[]{"e-mail, fio"})));
    }

    public AppData getApp(String key) {
        return apps.get(key);
    }

}

package ru.itis.dis403.lab2_8.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dis403.lab2_8.model.Weather;
import ru.itis.dis403.lab2_8.service.WeatherService;

@RestController
public class WeatherRestController {

    private final WeatherService weatherService;

    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public ResponseEntity<Weather> getWeather() {
        return ResponseEntity.ok(weatherService.getWeather());
    }
}

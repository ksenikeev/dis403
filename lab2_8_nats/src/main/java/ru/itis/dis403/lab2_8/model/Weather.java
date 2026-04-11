package ru.itis.dis403.lab2_8.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter@Builder
public class Weather {
    private String city;
    private Double temp;
    private Double pressure;
    private Double windSpeed;
    private String windDirection;
    private LocalDateTime dateTime;

    public Weather() {
    }
}

package ru.itis.dis403.lab2_9.httpclient.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter@Builder@NoArgsConstructor@AllArgsConstructor
public class Weather {
    private String city;
    private Double temp;
    private Double pressure;
    private Double windSpeed;
    private String windDirection;
    private LocalDateTime dateTime;
}

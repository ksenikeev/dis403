package ru.itis.dis403.lab01_04.model;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Airplane {

    private String code;
    private String model;
    private Integer range;
    private Integer speed;

    public Airplane() {
    }

    public Airplane(String code, String model, Integer range, Integer speed) {
        this.code = code;
        this.model = model;
        this.range = range;
        this.speed = speed;
    }
}

package ru.itis.dis403.lab01_04.model;

public class Airplane {

    private String code;
    private String model;
    private Integer range;
    private Integer speed;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Airplane() {
    }

    public Airplane(String code, String model, Integer range, Integer speed) {
        this.code = code;
        this.model = model;
        this.range = range;
        this.speed = speed;
    }
}

package ru.itis.dis403.lab2_6.dto;

public class HotelDto {
    private Long id;
    private String name;

    public HotelDto() {
    }

    public HotelDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

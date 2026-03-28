package ru.itis.dis403.lab2_6.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class BookingDto {
    private Long id;
    private Date arrivaldate;
    private Date stayingdate;
    private Date departuredate;

    private Long personId;
    private String name;
    private String gender;
    private Date birthDate;
}

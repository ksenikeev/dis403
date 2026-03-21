package ru.itis.dis403.lab2_6.dto;

import ru.itis.dis403.lab2_6.model.Booking;

import java.util.List;

public class BookingsResponse {
    private List<Booking> bookings;

    public BookingsResponse(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

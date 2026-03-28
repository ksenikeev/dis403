package ru.itis.dis403.lab2_6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dis403.lab2_6.dto.BookingPersonViewDto;
import ru.itis.dis403.lab2_6.dto.BookingsResponse;
import ru.itis.dis403.lab2_6.dto.BookingsViewResponse;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.repository.BookingRepository;
import ru.itis.dis403.lab2_6.service.BookingPersonViewService;
import ru.itis.dis403.lab2_6.service.UserDetailImpl;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingPersonViewController {

    private final BookingPersonViewService bookingPersonViewService;

    public BookingPersonViewController(BookingPersonViewService bookingPersonViewService) {
        this.bookingPersonViewService = bookingPersonViewService;
    }

    @GetMapping("/allview")
    public ResponseEntity<BookingsViewResponse> getBookings() {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        List<BookingPersonViewDto> bookings = bookingPersonViewService.findByHotelId(userDetails.getUser().getHotel().getId());

        bookings.forEach(b-> System.out.println(b.getId()));

        return ResponseEntity.ok(new BookingsViewResponse(bookings));
    }
}

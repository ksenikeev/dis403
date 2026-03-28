package ru.itis.dis403.lab2_6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.itis.dis403.lab2_6.dto.AuthRequest;
import ru.itis.dis403.lab2_6.dto.AuthResponse;
import ru.itis.dis403.lab2_6.dto.BookingDto;
import ru.itis.dis403.lab2_6.dto.BookingsResponse;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.repository.BookingRepository;
import ru.itis.dis403.lab2_6.service.BookingService;
import ru.itis.dis403.lab2_6.service.JWTService;
import ru.itis.dis403.lab2_6.service.UserDetailImpl;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;

    public BookingController(BookingRepository bookingRepository, BookingService bookingService) {
        this.bookingRepository = bookingRepository;
        this.bookingService = bookingService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookingDto> getBookingById(@RequestParam("id") Long id) {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        System.out.println(userDetails.getUser());
        BookingDto booking = bookingService.getBookingById(id, userDetails.getUser());

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/all")
    public ResponseEntity<BookingsResponse> getBookings() {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        System.out.println(userDetails.getUser());
        List<Booking> bookings = bookingRepository.findByHotel(userDetails.getUser().getHotel());

        bookings.forEach(b-> System.out.println(b.getId()));

        return ResponseEntity.ok(new BookingsResponse(bookings));
    }
}

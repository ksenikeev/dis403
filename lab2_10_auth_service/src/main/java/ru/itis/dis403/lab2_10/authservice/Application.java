package ru.itis.dis403.lab2_10.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("user"));
        System.out.println(encoder.encode("admin"));


        ApplicationContext context = SpringApplication.run(Application.class, args);

/*
        BookingRepository bookingRepository = context.getBean(BookingRepository.class);
        HotelRepository hotelRepository = context.getBean(HotelRepository.class);

        ObjectMapper mapper = new ObjectMapper();
        BookingsData data = mapper.readValue(new File("bookings.json"), BookingsData.class);

        data.getBookings().forEach(b -> {
            Hotel hotel = b.getHotel();
            if (!hotelRepository.existsById(b.getHotel().getId())) {
                hotel = hotelRepository.save(hotel);
                b.setHotel(hotel);
            }
            bookingRepository.save(b);
        });


*/
    }

}

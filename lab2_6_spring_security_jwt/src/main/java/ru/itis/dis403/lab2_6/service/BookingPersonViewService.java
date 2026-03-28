package ru.itis.dis403.lab2_6.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_6.dto.BookingPersonViewDto;
import ru.itis.dis403.lab2_6.model.BookingPersonView;
import ru.itis.dis403.lab2_6.repository.BookingPersonViewRepository;

import java.util.List;

@Service
public class BookingPersonViewService {

    private final BookingPersonViewRepository bookingPersonViewRepository;

    public BookingPersonViewService(BookingPersonViewRepository bookingPersonViewRepository) {
        this.bookingPersonViewRepository = bookingPersonViewRepository;
    }

    public List<BookingPersonViewDto> findByHotelId(Long hotelId) {
        return bookingPersonViewRepository.findByHotelId(hotelId).stream()
                .map(b ->
                    BookingPersonViewDto.builder()
                        .id(b.getId())
                        .arrivaldate(b.getArrivaldate())
                        .stayingdate(b.getStayingdate())
                            .room(b.getRoom())
                            .name(b.getName())
                            .birthdate(b.getBirthdate())
                            .hotelId(b.getHotelId())
                            .gender(b.getGender())
                            .build()
                ).toList();
    }
}

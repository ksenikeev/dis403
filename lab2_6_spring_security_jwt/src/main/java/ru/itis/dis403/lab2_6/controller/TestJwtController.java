package ru.itis.dis403.lab2_6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dis403.lab2_6.dto.HotelDto;

@RestController
public class TestJwtController {

    @GetMapping("/test")
    public ResponseEntity<HotelDto> login() {

        return ResponseEntity.ok(new HotelDto(1L, "Гостиница"));
    }

}

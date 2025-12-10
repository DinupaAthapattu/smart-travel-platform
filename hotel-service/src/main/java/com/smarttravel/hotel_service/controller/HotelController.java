package com.smarttravel.hotel_service.controller;

import com.smarttravel.hotel_service.dto.HotelAvailabilityResponseDto;
import com.smarttravel.hotel_service.dto.HotelRequestDto;
import com.smarttravel.hotel_service.dto.HotelResponseDto;
import com.smarttravel.hotel_service.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelResponseDto> createHotel(@Valid @RequestBody HotelRequestDto requestDto) {
        HotelResponseDto response = hotelService.createHotel(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDto> getHotelById(@PathVariable Long id) {
        HotelResponseDto response = hotelService.getHotelById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        List<HotelResponseDto> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    // key Feign endpoint for Booking Service
    @GetMapping("/{id}/availability")
    public ResponseEntity<HotelAvailabilityResponseDto> checkAvailability(@PathVariable Long id) {
        HotelAvailabilityResponseDto response = hotelService.checkAvailability(id);
        return ResponseEntity.ok(response);
    }
}

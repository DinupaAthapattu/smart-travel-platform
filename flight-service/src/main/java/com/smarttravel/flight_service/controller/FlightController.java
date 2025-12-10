package com.smarttravel.flight_service.controller;

import com.smarttravel.flight_service.dto.FlightAvailabilityResponseDto;
import com.smarttravel.flight_service.dto.FlightRequestDto;
import com.smarttravel.flight_service.dto.FlightResponseDto;
import com.smarttravel.flight_service.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@Valid @RequestBody FlightRequestDto requestDto) {
        FlightResponseDto response = flightService.createFlight(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Long id) {
        FlightResponseDto response = flightService.getFlightById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAllFlights() {
        List<FlightResponseDto> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }


    @GetMapping("/{id}/availability")
    public ResponseEntity<FlightAvailabilityResponseDto> checkAvailability(@PathVariable Long id) {
        FlightAvailabilityResponseDto response = flightService.checkAvailability(id);
        return ResponseEntity.ok(response);
    }
}

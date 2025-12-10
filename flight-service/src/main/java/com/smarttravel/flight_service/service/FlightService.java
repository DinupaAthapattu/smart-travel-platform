package com.smarttravel.flight_service.service;

import com.smarttravel.flight_service.dto.FlightAvailabilityResponseDto;
import com.smarttravel.flight_service.dto.FlightRequestDto;
import com.smarttravel.flight_service.dto.FlightResponseDto;

import java.util.List;

public interface FlightService {

    FlightResponseDto createFlight(FlightRequestDto requestDto);

    FlightResponseDto getFlightById(Long id);

    List<FlightResponseDto> getAllFlights();

    FlightAvailabilityResponseDto checkAvailability(Long id);
}

package com.smarttravel.flight_service.service.impl;

import com.smarttravel.flight_service.dto.FlightAvailabilityResponseDto;
import com.smarttravel.flight_service.dto.FlightRequestDto;
import com.smarttravel.flight_service.dto.FlightResponseDto;
import com.smarttravel.flight_service.entity.Flight;
import com.smarttravel.flight_service.exception.FlightNotFoundException;
import com.smarttravel.flight_service.repository.FlightRepository;
import com.smarttravel.flight_service.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightResponseDto createFlight(FlightRequestDto requestDto) {
        Flight flight = new Flight();
        flight.setFlightNumber(requestDto.getFlightNumber());
        flight.setOrigin(requestDto.getOrigin());
        flight.setDestination(requestDto.getDestination());
        flight.setPrice(requestDto.getPrice());
        flight.setSeatsAvailable(requestDto.getSeatsAvailable());

        Flight saved = flightRepository.save(flight);

        return new FlightResponseDto(
                saved.getId(),
                saved.getFlightNumber(),
                saved.getOrigin(),
                saved.getDestination(),
                saved.getPrice(),
                saved.getSeatsAvailable()
        );
    }

    @Override
    public FlightResponseDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        return new FlightResponseDto(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getPrice(),
                flight.getSeatsAvailable()
        );
    }

    @Override
    public List<FlightResponseDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flight -> new FlightResponseDto(
                        flight.getId(),
                        flight.getFlightNumber(),
                        flight.getOrigin(),
                        flight.getDestination(),
                        flight.getPrice(),
                        flight.getSeatsAvailable()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public FlightAvailabilityResponseDto checkAvailability(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        boolean available = flight.getSeatsAvailable() != null && flight.getSeatsAvailable() > 0;

        return new FlightAvailabilityResponseDto(
                flight.getId(),
                available,
                flight.getPrice()
        );
    }
}

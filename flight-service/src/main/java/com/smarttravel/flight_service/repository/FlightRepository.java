package com.smarttravel.flight_service.repository;

import com.smarttravel.flight_service.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}

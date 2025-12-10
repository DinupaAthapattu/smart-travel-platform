package com.smarttravel.booking_service.client;

import com.smarttravel.booking_service.dto.FlightAvailabilityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "flight-service",
        url = "http://localhost:8082"   // flight-service runs on 8082
)
public interface FlightClient {

    @GetMapping("/flights/{id}/availability")
    FlightAvailabilityDto checkAvailability(@PathVariable("id") Long flightId);
}
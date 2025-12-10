package com.smarttravel.booking_service.client;

import com.smarttravel.booking_service.dto.HotelAvailabilityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "hotel-service",
        url = "http://localhost:8083"   // hotel-service runs on 8083
)
public interface HotelClient {

    @GetMapping("/hotels/{id}/availability")
    HotelAvailabilityDto checkAvailability(@PathVariable("id") Long hotelId);
}

package com.smarttravel.hotel_service.service;

import com.smarttravel.hotel_service.dto.HotelAvailabilityResponseDto;
import com.smarttravel.hotel_service.dto.HotelRequestDto;
import com.smarttravel.hotel_service.dto.HotelResponseDto;

import java.util.List;

public interface HotelService {

    HotelResponseDto createHotel(HotelRequestDto requestDto);

    HotelResponseDto getHotelById(Long id);

    List<HotelResponseDto> getAllHotels();

    HotelAvailabilityResponseDto checkAvailability(Long id);
}

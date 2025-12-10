package com.smarttravel.hotel_service.service.impl;

import com.smarttravel.hotel_service.dto.HotelAvailabilityResponseDto;
import com.smarttravel.hotel_service.dto.HotelRequestDto;
import com.smarttravel.hotel_service.dto.HotelResponseDto;
import com.smarttravel.hotel_service.entity.Hotel;
import com.smarttravel.hotel_service.exception.HotelNotFoundException;
import com.smarttravel.hotel_service.repository.HotelRepository;
import com.smarttravel.hotel_service.service.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelResponseDto createHotel(HotelRequestDto requestDto) {
        Hotel hotel = new Hotel();
        hotel.setName(requestDto.getName());
        hotel.setCity(requestDto.getCity());
        hotel.setPricePerNight(requestDto.getPricePerNight());
        hotel.setRoomsAvailable(requestDto.getRoomsAvailable());

        Hotel saved = hotelRepository.save(hotel);

        return new HotelResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getCity(),
                saved.getPricePerNight(),
                saved.getRoomsAvailable()
        );
    }

    @Override
    public HotelResponseDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        return new HotelResponseDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getCity(),
                hotel.getPricePerNight(),
                hotel.getRoomsAvailable()
        );
    }

    @Override
    public List<HotelResponseDto> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(hotel -> new HotelResponseDto(
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getCity(),
                        hotel.getPricePerNight(),
                        hotel.getRoomsAvailable()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public HotelAvailabilityResponseDto checkAvailability(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        boolean available = hotel.getRoomsAvailable() != null && hotel.getRoomsAvailable() > 0;

        return new HotelAvailabilityResponseDto(
                hotel.getId(),
                available,
                hotel.getPricePerNight()
        );
    }
}

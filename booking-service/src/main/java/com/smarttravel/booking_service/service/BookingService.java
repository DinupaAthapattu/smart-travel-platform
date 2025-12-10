package com.smarttravel.booking_service.service;

import com.smarttravel.booking_service.dto.BookingRequestDto;
import com.smarttravel.booking_service.dto.BookingResponseDto;
import com.smarttravel.booking_service.model.BookingStatus;

import java.util.List;

public interface BookingService {

    BookingResponseDto createBooking(BookingRequestDto requestDto);

    BookingResponseDto getBookingById(Long id);

    // For Payment Service → Booking Service (WebClient)
    BookingResponseDto updateBookingStatus(Long id, BookingStatus status);

    // Get all bookings
    List<BookingResponseDto> getAllBookings();
}

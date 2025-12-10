package com.smarttravel.booking_service.service.impl;

import com.smarttravel.booking_service.client.FlightClient;
import com.smarttravel.booking_service.client.HotelClient;
import com.smarttravel.booking_service.dto.*;
import com.smarttravel.booking_service.entity.Booking;
import com.smarttravel.booking_service.exception.BookingNotFoundException;
import com.smarttravel.booking_service.exception.ExternalServiceException;
import com.smarttravel.booking_service.model.BookingStatus;
import com.smarttravel.booking_service.repository.BookingRepository;
import com.smarttravel.booking_service.service.BookingService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.smarttravel.booking_service.dto.BookingResponseDto;
import com.smarttravel.booking_service.entity.Booking;
import java.util.List;

import java.time.LocalDate;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final WebClient webClient;
    private final FlightClient flightClient;
    private final HotelClient hotelClient;

    // Base URLs for other services
    private static final String USER_SERVICE_BASE_URL = "http://localhost:8081";
    private static final String NOTIFICATION_SERVICE_BASE_URL = "http://localhost:8086";


    public BookingServiceImpl(BookingRepository bookingRepository,
                              WebClient webClient,
                              FlightClient flightClient,
                              HotelClient hotelClient) {
        this.bookingRepository = bookingRepository;
        this.webClient = webClient;
        this.flightClient = flightClient;
        this.hotelClient = hotelClient;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto requestDto) {

        // 1. Parse travel date
        LocalDate travelDate = LocalDate.parse(requestDto.getTravelDate());

        // 2. Booking Service → User Service (WebClient) – validate user
        UserDto user = fetchUserById(requestDto.getUserId());

        // 3. Booking Service → Flight Service (Feign) – check availability
        FlightAvailabilityDto flightAvailability = flightClient.checkAvailability(requestDto.getFlightId());
        if (flightAvailability == null || !flightAvailability.isAvailable()) {
            throw new ExternalServiceException("Flight not available for id: " + requestDto.getFlightId());
        }

        // 4. Booking Service → Hotel Service (Feign) – check availability
        HotelAvailabilityDto hotelAvailability = hotelClient.checkAvailability(requestDto.getHotelId());
        if (hotelAvailability == null || !hotelAvailability.isAvailable()) {
            throw new ExternalServiceException("Hotel not available for id: " + requestDto.getHotelId());
        }

        // 5. Calculate total amount (simple sum: flight price + one night hotel)
        Double totalAmount = safeDouble(flightAvailability.getPrice()) +
                safeDouble(hotelAvailability.getPricePerNight());

        // 6. Create booking with status PENDING
        Booking booking = new Booking();
        booking.setUserId(requestDto.getUserId());
        booking.setFlightId(requestDto.getFlightId());
        booking.setHotelId(requestDto.getHotelId());
        booking.setTravelDate(travelDate);
        booking.setTotalAmount(totalAmount);
        booking.setStatus(BookingStatus.PENDING);

        Booking saved = bookingRepository.save(booking);

        // 7. Booking Service → Notification Service (WebClient REST call)
        sendBookingNotification(user, saved);


        return mapToResponseDto(saved);
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        return mapToResponseDto(booking);
    }

    @Override
    public BookingResponseDto updateBookingStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        booking.setStatus(status);
        Booking updated = bookingRepository.save(booking);
        return mapToResponseDto(updated);
    }

    // --------- Helper methods ---------

    private UserDto fetchUserById(Long userId) {
        try {
            return webClient.get()
                    .uri(USER_SERVICE_BASE_URL + "/users/{id}", userId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new ExternalServiceException("User not found with id: " + userId))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new ExternalServiceException("User service error: " + body))
                    )
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (WebClientResponseException ex) {
            throw new ExternalServiceException("Failed to call User Service: " + ex.getMessage());
        } catch (Exception ex) {
            throw new ExternalServiceException("Unexpected error calling User Service: " + ex.getMessage());
        }
    }

    private void sendBookingNotification(UserDto user, Booking booking) {
        try {
            NotificationRequestDto notificationRequest = new NotificationRequestDto(
                    user.getId(),
                    user.getEmail(),
                    "Your booking with id " + booking.getId() + " is created with status: " + booking.getStatus(),
                    booking.getId()
            );

            webClient.post()
                    .uri(NOTIFICATION_SERVICE_BASE_URL + "/notifications")
                    .bodyValue(notificationRequest)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception ex) {
            System.out.println("Failed to send notification: " + ex.getMessage());
        }
    }

    private Double safeDouble(Double value) {
        return value != null ? value : 0.0;
    }

    private BookingResponseDto mapToResponseDto(Booking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getUserId(),
                booking.getFlightId(),
                booking.getHotelId(),
                booking.getTravelDate(),
                booking.getTotalAmount(),
                booking.getStatus()
        );
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

}

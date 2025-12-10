package com.smarttravel.payment_service.service.impl;

import com.smarttravel.payment_service.dto.BookingUpdateResponseDto;
import com.smarttravel.payment_service.dto.PaymentRequestDto;
import com.smarttravel.payment_service.dto.PaymentResponseDto;
import com.smarttravel.payment_service.exception.PaymentFailedException;
import com.smarttravel.payment_service.service.PaymentService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final WebClient webClient;

    private static final String BOOKING_SERVICE_BASE_URL = "http://localhost:8084";

    public PaymentServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto requestDto) {

        Long bookingId = requestDto.getBookingId();
        Double amount = requestDto.getAmount();


        if (amount == null || amount <= 0) {
            throw new PaymentFailedException("Invalid payment amount: " + amount);
        }

        try {
            // Payment is considered successful → update Booking status to CONFIRMED via WebClient
            BookingUpdateResponseDto bookingResponse = webClient.put()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(8084)
                            .path("/bookings/{id}/status")
                            .queryParam("status", "CONFIRMED")
                            .build(bookingId))
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new PaymentFailedException("Booking not found or invalid when updating status. Booking id: " + bookingId))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new PaymentFailedException("Booking Service error while updating status: " + body))
                    )
                    .bodyToMono(BookingUpdateResponseDto.class)
                    .block();

            String msg = "Payment successful and booking status updated to " +
                    (bookingResponse != null ? bookingResponse.getStatus() : "CONFIRMED");

            return new PaymentResponseDto(
                    bookingId,
                    "SUCCESS",
                    msg
            );

        } catch (WebClientResponseException ex) {
            throw new PaymentFailedException("Error calling Booking Service: " + ex.getMessage());
        } catch (Exception ex) {
            throw new PaymentFailedException("Unexpected error during payment: " + ex.getMessage());
        }
    }
}

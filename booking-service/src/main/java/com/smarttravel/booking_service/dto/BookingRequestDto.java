package com.smarttravel.booking_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BookingRequestDto {

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long flightId;

    @NotNull
    @Min(1)
    private Long hotelId;

    /**
     * Travel date in ISO format: yyyy-MM-dd
     * Example: "2025-01-10"
     */
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "travelDate must be in format yyyy-MM-dd")
    private String travelDate;

    public BookingRequestDto() {
    }

    public BookingRequestDto(Long userId, Long flightId, Long hotelId, String travelDate) {
        this.userId = userId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.travelDate = travelDate;
    }

    // Getters

    public Long getUserId() {
        return userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getTravelDate() {
        return travelDate;
    }

    // Setters

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }
}

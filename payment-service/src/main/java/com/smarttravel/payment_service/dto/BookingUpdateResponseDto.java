package com.smarttravel.payment_service.dto;

import java.time.LocalDate;

public class BookingUpdateResponseDto {

    private Long id;
    private Long userId;
    private Long flightId;
    private Long hotelId;
    private LocalDate travelDate;
    private Double totalAmount;
    private String status; // PENDING, CONFIRMED, FAILED

    public BookingUpdateResponseDto() {
    }

    public BookingUpdateResponseDto(Long id,
                                    Long userId,
                                    Long flightId,
                                    Long hotelId,
                                    LocalDate travelDate,
                                    Double totalAmount,
                                    String status) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.travelDate = travelDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

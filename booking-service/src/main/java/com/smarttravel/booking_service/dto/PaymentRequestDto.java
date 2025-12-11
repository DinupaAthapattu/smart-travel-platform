package com.smarttravel.booking_service.dto;

public class PaymentRequestDto {

    private Long bookingId;
    private Double amount;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(Long bookingId, Double amount) {
        this.bookingId = bookingId;
        this.amount = amount;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

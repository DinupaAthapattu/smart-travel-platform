package com.smarttravel.payment_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentRequestDto {

    @NotNull
    @Positive
    private Long bookingId;

    @NotNull
    @Positive
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

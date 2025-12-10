package com.smarttravel.payment_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PaymentRequestDto {

    @NotNull
    @Min(1)
    private Long bookingId;

    @NotNull
    @Min(1)
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

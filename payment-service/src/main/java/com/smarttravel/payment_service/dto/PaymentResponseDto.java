package com.smarttravel.payment_service.dto;

public class PaymentResponseDto {

    private Long bookingId;
    private String paymentStatus; // e.g., "SUCCESS" or "FAILED"
    private String message;

    public PaymentResponseDto() {
    }

    public PaymentResponseDto(Long bookingId, String paymentStatus, String message) {
        this.bookingId = bookingId;
        this.paymentStatus = paymentStatus;
        this.message = message;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

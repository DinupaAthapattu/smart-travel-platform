package com.smarttravel.notification_service.dto;

public class NotificationRequestDto {

    private Long userId;
    private String email;
    private String message;
    private Long bookingId;

    public NotificationRequestDto() {
    }

    public NotificationRequestDto(Long userId, String email, String message, Long bookingId) {
        this.userId = userId;
        this.email = email;
        this.message = message;
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}

package com.smarttravel.booking_service.entity;

import com.smarttravel.booking_service.model.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long flightId;

    private Long hotelId;

    private LocalDate travelDate;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking() {
    }

    public Booking(Long id,
                   Long userId,
                   Long flightId,
                   Long hotelId,
                   LocalDate travelDate,
                   Double totalAmount,
                   BookingStatus status) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.travelDate = travelDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters

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

    public BookingStatus getStatus() {
        return status;
    }

    // Setters

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

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}

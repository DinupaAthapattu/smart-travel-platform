package com.smarttravel.booking_service.dto;

public class FlightAvailabilityDto {

    private Long flightId;
    private boolean available;
    private Double price;

    public FlightAvailabilityDto() {
    }

    public FlightAvailabilityDto(Long flightId, boolean available, Double price) {
        this.flightId = flightId;
        this.available = available;
        this.price = price;
    }

    // Getters

    public Long getFlightId() {
        return flightId;
    }

    public boolean isAvailable() {
        return available;
    }

    public Double getPrice() {
        return price;
    }

    // Setters

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

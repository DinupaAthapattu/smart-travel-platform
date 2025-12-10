package com.smarttravel.flight_service.dto;

public class FlightAvailabilityResponseDto {

    private Long flightId;
    private boolean available;
    private Double price;

    public FlightAvailabilityResponseDto() {
    }

    public FlightAvailabilityResponseDto(Long flightId, boolean available, Double price) {
        this.flightId = flightId;
        this.available = available;
        this.price = price;
    }

    // Getters & setters

    public Long getFlightId() {
        return flightId;
    }

    public boolean isAvailable() {
        return available;
    }

    public Double getPrice() {
        return price;
    }

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

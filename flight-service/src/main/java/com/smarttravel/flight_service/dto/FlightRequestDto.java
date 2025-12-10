package com.smarttravel.flight_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlightRequestDto {

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    @Min(0)
    private Integer seatsAvailable;

    public FlightRequestDto() {
    }

    public FlightRequestDto(String flightNumber, String origin, String destination,
                            Double price, Integer seatsAvailable) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }

    // Getters & setters

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}

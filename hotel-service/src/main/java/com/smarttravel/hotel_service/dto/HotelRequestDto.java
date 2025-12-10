package com.smarttravel.hotel_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HotelRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotNull
    @Min(0)
    private Double pricePerNight;

    @NotNull
    @Min(0)
    private Integer roomsAvailable;

    public HotelRequestDto() {
    }

    public HotelRequestDto(String name, String city,
                           Double pricePerNight, Integer roomsAvailable) {
        this.name = name;
        this.city = city;
        this.pricePerNight = pricePerNight;
        this.roomsAvailable = roomsAvailable;
    }

    // Getters & setters

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public Integer getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setRoomsAvailable(Integer roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }
}

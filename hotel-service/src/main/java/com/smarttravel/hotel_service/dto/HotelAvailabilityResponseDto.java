package com.smarttravel.hotel_service.dto;

public class HotelAvailabilityResponseDto {

    private Long hotelId;
    private boolean available;
    private Double pricePerNight;

    public HotelAvailabilityResponseDto() {
    }

    public HotelAvailabilityResponseDto(Long hotelId, boolean available, Double pricePerNight) {
        this.hotelId = hotelId;
        this.available = available;
        this.pricePerNight = pricePerNight;
    }

    // Getters & setters

    public Long getHotelId() {
        return hotelId;
    }

    public boolean isAvailable() {
        return available;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}

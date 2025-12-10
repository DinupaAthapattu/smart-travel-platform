package com.smarttravel.booking_service.dto;

public class HotelAvailabilityDto {

    private Long hotelId;
    private boolean available;
    private Double pricePerNight;

    public HotelAvailabilityDto() {
    }

    public HotelAvailabilityDto(Long hotelId, boolean available, Double pricePerNight) {
        this.hotelId = hotelId;
        this.available = available;
        this.pricePerNight = pricePerNight;
    }

    // Getters

    public Long getHotelId() {
        return hotelId;
    }

    public boolean isAvailable() {
        return available;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    // Setters

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

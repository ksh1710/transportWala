package com.transportWala.tw_matching.Entity;


import lombok.Data;

@Data
public class BookingRequestDTO {
    private String bookingId;
    private double pickupLatitude;
    private double pickupLongitude;
    private double dropLatitude;
    private double dropLongitude;

    // Getters and Setters
}

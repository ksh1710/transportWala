package com.transportWala.tw_user.Entity;

import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private String pickupLocation;
    private String dropoffLocation;
    private String vehicleType;
}

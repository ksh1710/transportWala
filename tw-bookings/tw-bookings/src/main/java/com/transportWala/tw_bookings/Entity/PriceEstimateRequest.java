package com.transportWala.tw_bookings.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceEstimateRequest {
    private double distance;
    private String vehicleType;
    private LocalDateTime time;
    private String state;
}

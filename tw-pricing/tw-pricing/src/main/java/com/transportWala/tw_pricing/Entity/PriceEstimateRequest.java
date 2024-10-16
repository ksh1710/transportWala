package com.transportWala.tw_pricing.Entity;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class PriceEstimateRequest {
    private double distance;
    private String vehicleType;
    private LocalDateTime time;
    private String state;
}
package com.transportWala.tw_driver.Entity;

import lombok.Data;

@Data
public class DriverStatusDTO {
    private Long driverId;
    private String status;  // "available", "busy", "offline"
}
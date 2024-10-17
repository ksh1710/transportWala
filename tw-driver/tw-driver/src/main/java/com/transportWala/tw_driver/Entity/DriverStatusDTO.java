package com.transportWala.tw_driver.Entity;

import lombok.Data;

@Data
public class DriverStatusDTO {
    private String driverId;
    private String status;  // "available", "busy", "offline"
}
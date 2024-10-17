package com.transportWala.tw_driver.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverStatusDTO {
    private Long driverId;
    private String status;  // "available", "busy", "offline"
}
package com.transportWala.tw_driver.Entity;

import lombok.Data;

@Data
public class DriverLocationDTO {
    private Long driverId;
    private double latitude;
    private double longitude;
}

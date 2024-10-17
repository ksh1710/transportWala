package com.transportWala.tw_driver.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverLocationDTO {
    private Long driverId;
    private double latitude;
    private double longitude;
}

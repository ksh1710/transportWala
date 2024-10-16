package com.transportWala.tw_matching.Entity;


import com.netflix.discovery.EurekaNamespace;
import jdk.jfr.DataAmount;
import lombok.Data;


@Data
public class DriverResponseDTO {
    private String driverId;
    private boolean accepted;

    // Getters and Setters
}

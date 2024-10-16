package com.transportWala.tw_driver.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driverId;

    private String name;
    private String vehicleType;
    private String status; // e.g., available, on-job
}
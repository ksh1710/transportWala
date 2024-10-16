package com.transportWala.tw_bookings.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private Long userId;
    private Long driverId;
    private String vehicleType;
    private double estimatedPrice;
    private String status; // PENDING, CONFIRMED, etc.

}
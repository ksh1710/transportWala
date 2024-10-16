package com.transportWala.tw_bookings.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {


    private Long bookingId;
    //    private String pickupLocation;
//    private String dropoffLocation;
    private double estimatedPrice;
    private String status;

}
package com.transportWala.tw_user.Entity;

import lombok.Data;

@Data
public class BookingResponse {
    private Long bookingId;
    private String status;
    private String estimatedPrice;
    private String assignedDriverId;
}

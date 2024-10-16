package com.transportWala.tw_bookings.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEstimateResponse {
    private double estimatedPrice;
    private String message;
}

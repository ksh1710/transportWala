package com.transportWala.tw_pricing.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEstimateResponse {
    private double estimatedPrice;
    private String message;

}
package com.transportWala.tw_pricing.Controller;


import com.transportWala.tw_pricing.Entity.PriceEstimateRequest;
import com.transportWala.tw_pricing.Entity.PriceEstimateResponse;
import com.transportWala.tw_pricing.Service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @PostMapping("/estimate")
    public ResponseEntity<PriceEstimateResponse> getPriceEstimate(@RequestBody PriceEstimateRequest request) {
        try {
            double estimatedPrice = pricingService.calculatePrice(request);
            PriceEstimateResponse response = new PriceEstimateResponse(estimatedPrice, "Price calculated successfully");
            return new ResponseEntity<PriceEstimateResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PriceEstimateResponse(0.0, "Failed to calculate price"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
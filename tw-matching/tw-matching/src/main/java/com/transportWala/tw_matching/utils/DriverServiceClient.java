package com.transportWala.tw_matching.utils;


import com.transportWala.tw_matching.Entity.BookingRequestDTO;
import com.transportWala.tw_matching.Entity.DriverResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DriverServiceClient {

    private final RestTemplate restTemplate;

    public DriverServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DriverResponseDTO sendBookingRequest(String driverId, BookingRequestDTO bookingRequest) {
        String url = "http://driver-service/api/drivers/" + driverId + "/booking-request";
        return restTemplate.postForObject(url, bookingRequest, DriverResponseDTO.class);
    }
}

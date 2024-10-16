package com.transportWala.tw_matching.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookingServiceClient {

    private final RestTemplate restTemplate;

    public BookingServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void assignDriverToBooking(String bookingId, String driverId) {
        String url = "http://booking-service/api/bookings/" + bookingId + "/assign-driver";
        restTemplate.postForLocation(url, driverId);
    }
}
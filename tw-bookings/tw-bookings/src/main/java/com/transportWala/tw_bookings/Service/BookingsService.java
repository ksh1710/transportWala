package com.transportWala.tw_bookings.Service;

import com.transportWala.tw_bookings.Entity.*;
import com.transportWala.tw_bookings.Repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BookingsService {

    @Autowired
    private RestTemplate restTemplate;
    private static final double EARTH_RADIUS_KM = 6371.0; // Earth's radius in kilometers

    @Autowired
    private BookingsRepository bookingsRepository;

    public List<Bookings> getAllDrivers() {
        return bookingsRepository.findAll();
    }

    public Bookings getBookingById(Long id) {
        return bookingsRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public BookingResponseDTO createBookingRequest(BookingRequestDTO bookingRequest) {

        PriceEstimateRequest priceEstimateRequest = new PriceEstimateRequest();
        priceEstimateRequest.setDistance(calculateDistance(
                bookingRequest.getPickupLatitude(),
                bookingRequest.getPickupLongitude(),
                bookingRequest.getDropoffLatitude(),
                bookingRequest.getDropoffLongitude()
        ));
        priceEstimateRequest.setVehicleType(bookingRequest.getVehicleType());
        priceEstimateRequest.setTime(LocalDateTime.now());
        System.out.println("date time " + LocalDateTime.now());
        priceEstimateRequest.setState(bookingRequest.getState());


        PriceEstimateResponse priceResponse = restTemplate.postForObject(
                "http://TW-PRICING/pricing/estimate",
                priceEstimateRequest,
                PriceEstimateResponse.class
        );

        BookingResponseDTO bookingRequestResponse = new BookingResponseDTO();
        Long id = Math.abs(new Random().nextLong());
        bookingRequestResponse.setBookingId(id);
        System.out.println("id:" + id);
        assert priceResponse != null;
        bookingRequestResponse.setEstimatedPrice(priceResponse.getEstimatedPrice());
        bookingRequestResponse.setStatus("PENDING");

//        restTemplate.postForObject(
//                "http://matching-service/api/matching/assign-driver",
//                bookingRequest,
//                Void.class
//        );

//        return new BookingResponseDTO(
//                booking.getId(),
//                booking.getPickupLocation(),
//                booking.getDropoffLocation(),
//                booking.getEstimatedPrice(),
//                booking.getStatus()
//        );
//        assert priceResponse != null;
//        return priceResponse.getEstimatedPrice();
        return bookingRequestResponse;
    }

    private double calculateDistance(double pickupLatitude, double pickupLongitude, double dropoffLatitude, double dropoffLongitude) {
        double lat1 = Math.toRadians(pickupLatitude);
        double lon1 = Math.toRadians(pickupLongitude);
        double lat2 = Math.toRadians(dropoffLatitude);
        double lon2 = Math.toRadians(dropoffLongitude);

        // Haversine formula
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;
        System.out.println("distance: " + distance);
        return distance;
    }
}

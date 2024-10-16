package com.transportWala.tw_bookings.Service;

import com.transportWala.tw_bookings.Entity.*;
import com.transportWala.tw_bookings.Repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

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

    public double createBooking(BookingRequestDTO bookingRequest) {
        // Step 1: Prepare the price estimation request body
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


        // Step 2: Call the Pricing Service via Eureka using RestTemplate
        PriceEstimateResponse priceResponse = restTemplate.postForObject(
                "http://TW-PRICING/pricing/estimate",
                priceEstimateRequest,
                PriceEstimateResponse.class
        );

        // Step 3: Save the booking details with the estimated price
//        Bookings booking = new Bookings();
//        booking.setPickupLocation(bookingRequest.getPickupLocation());
//        booking.setDropoffLocation(bookingRequest.getDropoffLocation());
//        booking.setVehicleType(bookingRequest.getVehicleType());
//        booking.setEstimatedPrice(priceResponse.getEstimatedPrice());
//        booking.setStatus("PENDING");
//        booking = bookingRepository.save(booking);

        // Step 4: Call Matching Service to assign a driver using Eureka
//        restTemplate.postForObject(
//                "http://matching-service/api/matching/assign-driver",
//                bookingRequest,
//                Void.class
//        );

        // Step 5: Return the booking response to the user
//        return new BookingResponseDTO(
//                booking.getId(),
//                booking.getPickupLocation(),
//                booking.getDropoffLocation(),
//                booking.getEstimatedPrice(),
//                booking.getStatus()
//        );
        assert priceResponse != null;
        return priceResponse.getEstimatedPrice();
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

        // Calculate the distance
        double distance = EARTH_RADIUS_KM * c;
        System.out.println("distance: " + distance);
        return distance;
    }
}

package com.transportWala.tw_matching.Service;

import com.transportWala.tw_matching.Entity.BookingRequestDTO;
import com.transportWala.tw_matching.Entity.DriverResponseDTO;
import com.transportWala.tw_matching.utils.BookingServiceClient;
import com.transportWala.tw_matching.utils.DriverServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private DriverServiceClient driverServiceClient;
    @Autowired
    private BookingServiceClient bookingServiceClient;

    public MatchingService(RedisTemplate<String, String> redisTemplate,
                           DriverServiceClient driverServiceClient,
                           BookingServiceClient bookingServiceClient) {
        this.redisTemplate = redisTemplate;
        this.driverServiceClient = driverServiceClient;
        this.bookingServiceClient = bookingServiceClient;
    }

//    public void findAndAssignDriver(BookingRequestDTO bookingRequest) {
//        GeoOperations<String, String> geoOperations = redisTemplate.opsForGeo();
//        String pickupLocationKey = "drivers-locations";
//
//        Circle searchArea = new Circle(new Point(bookingRequest.getPickupLongitude(), bookingRequest.getPickupLatitude()),
//                new Distance(10, Metrics.KILOMETERS));
//
//
//        // Fetch nearby drivers from Redis based on the booking's pickup location
//        // Fetch nearby drivers from Redis
//        List<String> nearbyDrivers = geoOperations.radius(pickupLocationKey, searchArea,
//                        GeoRadiusCommandArgs.newGeoRadiusArgs().includeCoordinates().includeDistance())
//                .getContent().stream()
//                .map(GeoResult::getContent) // Extract driver ID
//                .collect(Collectors.toList());
//
//        // Asynchronously send booking requests to the nearby drivers
//        for (String driverId : nearbyDrivers) {
//            CompletableFuture.runAsync(() -> {
//                DriverResponseDTO driverResponse = driverServiceClient.sendBookingRequest(driverId, bookingRequest);
//                if (driverResponse.isAccepted()) {
//                    // Once a driver accepts, update the booking with driver details
//                    bookingServiceClient.assignDriverToBooking(bookingRequest.getBookingId(), driverResponse.getDriverId());
//                    return;
//                }
//            });
//        }
//    }
}

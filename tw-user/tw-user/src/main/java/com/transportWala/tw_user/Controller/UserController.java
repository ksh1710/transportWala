package com.transportWala.tw_user.Controller;


import com.transportWala.tw_user.Entity.BookingRequest;
import com.transportWala.tw_user.Entity.User;
import com.transportWala.tw_user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;


//    @PostMapping("/bookings")
//    public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {
////        String bookingServiceUrl = "http://booking-service/bookings/getAll"; // Eureka service name
//
//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(bookingServiceUrl, bookingRequest, String.class);
//            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Booking could not be created. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/bookings/{id}")
//    public ResponseEntity<String> getBookingDetails(@PathVariable("id") String bookingId) {
//        String bookingServiceUrl = "http://booking-service/api/bookings/" + bookingId; // Eureka service name
//
//        try {
//            ResponseEntity<String> response = restTemplate.getForEntity(bookingServiceUrl, String.class);
//            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Unable to fetch booking details. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("getUser/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}

package com.transportWala.tw_matching.Controller;


import com.transportWala.tw_matching.Entity.BookingRequestDTO;
import com.transportWala.tw_matching.Service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/matching")
public class MatchingController {

    @Autowired
    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

//    @PostMapping("/request-driver")
//    public ResponseEntity<String> matchDriverToBooking(@RequestBody BookingRequestDTO bookingRequest) {
//        matchingService.findAndAssignDriver(bookingRequest);
//        return ResponseEntity.ok("Matching driver to booking initiated.");
//    }

    @GetMapping("/test")
    public String testing(){
        return "test";
    }
}
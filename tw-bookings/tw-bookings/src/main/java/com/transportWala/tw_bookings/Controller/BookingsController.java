package com.transportWala.tw_bookings.Controller;


import com.transportWala.tw_bookings.Entity.BookingRequestDTO;
import com.transportWala.tw_bookings.Entity.Bookings;
import com.transportWala.tw_bookings.Service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingsService bookingsService;

    @GetMapping("/getAll")
    public List<Bookings> getAllBookings() {
        return bookingsService.getAllDrivers();
    }

    @GetMapping("get/{id}")
    public Bookings getBookingById(@PathVariable Long id) {
        return bookingsService.getBookingById(id);
    }

    @PostMapping("/create")
    public double createBooking(@RequestBody BookingRequestDTO booking) {
        return bookingsService.createBooking(booking);
    }
}

package com.transportWala.tw_driver.Controller;


import com.transportWala.tw_driver.Entity.Driver;
import com.transportWala.tw_driver.Entity.DriverLocationDTO;
import com.transportWala.tw_driver.Entity.DriverStatusDTO;
import com.transportWala.tw_driver.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping("/getAll")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("get/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @PostMapping("/register")
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

//    @PutMapping("/{id}/status")
//    public ResponseEntity<String> updateDriverStatus(@RequestBody DriverStatusDTO status) {
//        driverService.updateDriverStatus(status);
//        return ResponseEntity.ok("updated");
//    }


    // Driver sends their location update
    @PostMapping("/location")
    public ResponseEntity<String> updateLocation(@RequestBody DriverLocationDTO locationDTO) {
        driverService.updateLocation(locationDTO);
        return ResponseEntity.ok("Location updated successfully");
    }

    @PostMapping("/getLocation")
    public ResponseEntity<String> getLocation(@RequestBody DriverLocationDTO location) {
        DriverLocationDTO loc = driverService.getLocation(location.getDriverId());
        System.out.println(loc.toString());
        return ResponseEntity.ok("Location updated successfully");
    }

    @PostMapping("/status")
    public ResponseEntity<String> updateStatus(@RequestBody DriverStatusDTO statusDTO) {
        driverService.updateDriverStatus(statusDTO);
        return ResponseEntity.ok("Driver status updated successfully");
    }
}




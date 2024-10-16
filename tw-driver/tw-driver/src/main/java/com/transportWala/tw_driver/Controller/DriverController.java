package com.transportWala.tw_driver.Controller;


import com.transportWala.tw_driver.Entity.Driver;
import com.transportWala.tw_driver.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{id}/status")
    public Driver updateDriverStatus(@PathVariable Long id, @RequestParam String status) {
        return driverService.updateDriverStatus(id, status);
    }


}

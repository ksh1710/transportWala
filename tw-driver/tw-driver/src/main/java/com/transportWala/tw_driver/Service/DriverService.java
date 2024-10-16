package com.transportWala.tw_driver.Service;

import com.transportWala.tw_driver.Entity.Driver;
import com.transportWala.tw_driver.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriverStatus(Long id, String status) {
        Driver driver = getDriverById(id);
        driver.setStatus(status);
        return driverRepository.save(driver);
    }
}

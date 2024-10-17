package com.transportWala.tw_driver.Service;

import com.transportWala.tw_driver.Entity.Driver;
import com.transportWala.tw_driver.Entity.DriverLocationDTO;
import com.transportWala.tw_driver.Entity.DriverStatusDTO;
import com.transportWala.tw_driver.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;


    private final RedisTemplate<String, Object> redisTemplate;
    private static final String DRIVER_LOCATIONS_KEY = "drivers-locations";


    @Autowired
    public DriverService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    //    public Driver updateDriverStatus(DriverStatusDTO status) {
//        Driver driver = getDriverById(status.getDriverId());
//        driver.setStatus(status.getStatus());
//        return driverRepository.save(driver);
//    }
    public void updateDriverStatus(DriverStatusDTO statusDTO) {
        redisTemplate.opsForHash().put("driver-status", statusDTO.getDriverId().toString(), statusDTO.getStatus());
    }

//    public void getDriverStatus(Long id) {
//        redisTemplate.opsForHash().put("driver-status", statusDTO.getDriverId().toString(), statusDTO.getStatus());
//    }

    public void updateLocation(DriverLocationDTO locationDTO) {
        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();
        geoOperations.add(DRIVER_LOCATIONS_KEY, new Point(locationDTO.getLongitude(), locationDTO.getLatitude()), locationDTO.getDriverId().toString());
    }

    public DriverLocationDTO getLocation(Long id) {
        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();

        // Retrieve the location of the driver based on their ID
        List<Point> points = geoOperations.position(DRIVER_LOCATIONS_KEY, id.toString());

        if (points != null && !points.isEmpty()) {
            Point location = points.get(0);
            return new DriverLocationDTO(id, location.getX(), location.getY());
        } else {
            throw new RuntimeException("Location not found for driver ID: " + id);
        }
    }
}

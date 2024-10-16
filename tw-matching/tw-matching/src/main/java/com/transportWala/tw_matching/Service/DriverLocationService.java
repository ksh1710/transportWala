package com.transportWala.tw_matching.Service;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DriverLocationService {

    private final RedisTemplate<String, String> redisTemplate;

    public DriverLocationService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void updateDriverLocation(String driverId, double latitude, double longitude) {
        GeoOperations<String, String> geoOps = redisTemplate.opsForGeo();
        geoOps.add("drivers-locations", new Point(longitude, latitude), driverId);
    }
}


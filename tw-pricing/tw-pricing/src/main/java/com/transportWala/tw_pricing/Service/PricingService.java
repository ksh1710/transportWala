package com.transportWala.tw_pricing.Service;

import com.transportWala.tw_pricing.Entity.PriceEstimateRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PricingService {

    private static final double BASE_FARE = 50.0; // Base fare in rupees

    private static final Map<String, Double> STATE_MULTIPLIERS = new HashMap<>();
    private static final Map<Integer, Double> TIME_MULTIPLIERS = new HashMap<>();
    private static final Map<DayOfWeek, Double> DAY_MULTIPLIERS = new HashMap<>();
    private static final double DEMAND_MULTIPLIER = 1.5; // Increase price by 50% for high demand

    static {
        // Initialize state multipliers (example values)
        STATE_MULTIPLIERS.put("Maharashtra", 1.2);
        STATE_MULTIPLIERS.put("Delhi", 1.3);
        STATE_MULTIPLIERS.put("Karnataka", 1.1);
        // Add more states as needed

        // Initialize time multipliers
        TIME_MULTIPLIERS.put(0, 1.0);  // 12 AM - 6 AM
        TIME_MULTIPLIERS.put(6, 1.2);  // 6 AM - 10 AM
        TIME_MULTIPLIERS.put(10, 1.0); // 10 AM - 4 PM
        TIME_MULTIPLIERS.put(16, 1.3); // 4 PM - 8 PM
        TIME_MULTIPLIERS.put(20, 1.1); // 8 PM - 12 AM

        // Initialize day multipliers
        DAY_MULTIPLIERS.put(DayOfWeek.MONDAY, 1.0);
        DAY_MULTIPLIERS.put(DayOfWeek.FRIDAY, 1.1);
        DAY_MULTIPLIERS.put(DayOfWeek.SATURDAY, 1.2);
        DAY_MULTIPLIERS.put(DayOfWeek.SUNDAY, 1.1);
        // Add more days as needed
    }

    public double calculatePrice(PriceEstimateRequest request) {
        double distanceFactor = request.getDistance() * 10.0; // 10 rupees per km
        System.out.println("disancce --->" + request.getDistance());
        System.out.println("distance factor" + distanceFactor);
        double vehicleFactor = getVehiclePriceFactor(request.getVehicleType());

        double timeMultiplier = getTimeMultiplier(request.getTime().getHour());
        double dayMultiplier = getDayMultiplier(request.getTime().getDayOfWeek());
        double stateMultiplier = getStateMultiplier(request.getState());

        boolean highDemand = isSurgeHour(request.getTime());
        double demandMultiplier = highDemand ? DEMAND_MULTIPLIER : 1.0;

        double totalFare = (BASE_FARE + distanceFactor + vehicleFactor) *
                timeMultiplier * dayMultiplier * stateMultiplier * demandMultiplier;

        return Math.round(totalFare * 100.0) / 100.0; // Round to 2 decimal places
    }

    private double getVehiclePriceFactor(String vehicleType) {
        return switch (vehicleType) {
            case "van" -> 30.0;
            case "truck" -> 50.0;
            case "bike" -> 10.0;
            default -> 20.0;
        };
    }

    private double getTimeMultiplier(int hour) {
        return TIME_MULTIPLIERS.entrySet().stream()
                .filter(entry -> entry.getKey() <= hour)
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(1.0);
    }

    private double getDayMultiplier(DayOfWeek day) {
        return DAY_MULTIPLIERS.getOrDefault(day, 1.0);
    }

    private double getStateMultiplier(String state) {
        return STATE_MULTIPLIERS.getOrDefault(state, 1.0);
    }

    private boolean isSurgeHour(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 7 && hour < 10) || (hour >= 17 && hour < 20);
    }
}
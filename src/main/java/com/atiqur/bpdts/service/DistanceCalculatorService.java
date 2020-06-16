package com.atiqur.bpdts.service;

import com.atiqur.bpdts.domain.UserResponseDto;
import com.atiqur.bpdts.domain.model.Geolocation;
import org.springframework.stereotype.Service;

import static java.lang.Math.*;

@Service
public class DistanceCalculatorService {

    private final static int EARTH_RADIUS_IN_MILES = 3963;

    public double distance(UserResponseDto userResponseDto, Geolocation geolocation) {

        double startLongitude1 = toRadians(userResponseDto.getLongitude());
        double startLatitude1 = toRadians(userResponseDto.getLatitude());
        double endLongitude2 = toRadians(geolocation.getLongitude());
        double endLatitude2 = toRadians(geolocation.getLatitude());

        // Haversine formula
        double distanceLongitude = endLongitude2 - startLongitude1;
        double didtanceLatitude = endLatitude2 - startLatitude1;
        double val = haversin(didtanceLatitude) + cos(startLatitude1) * cos(endLatitude2) * haversin(distanceLongitude);

        return 2 * asin(sqrt(val)) * EARTH_RADIUS_IN_MILES;
    }

    private double haversin(double val) {
        return pow(sin(val / 2), 2);
    }
}
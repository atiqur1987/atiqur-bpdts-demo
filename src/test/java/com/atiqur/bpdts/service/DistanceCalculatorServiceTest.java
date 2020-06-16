package com.atiqur.bpdts.service;

import com.atiqur.bpdts.domain.UserResponseDto;
import com.atiqur.bpdts.domain.model.Geolocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceCalculatorServiceTest {

    private DistanceCalculatorService distanceCalculatorService = new DistanceCalculatorService();

    @Test
    void testDistance() {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setLatitude(51.542822);
        userDto.setLongitude(0.009270);

        Geolocation geolocationOfLondon = new Geolocation(51.507372, -0.127731);// central London

        assertEquals(6.385256904764926, distanceCalculatorService.distance(userDto, geolocationOfLondon));
    }
}
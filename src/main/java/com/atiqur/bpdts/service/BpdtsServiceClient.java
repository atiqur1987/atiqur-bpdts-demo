package com.atiqur.bpdts.service;

import com.atiqur.bpdts.config.Properties;
import com.atiqur.bpdts.domain.UserResponseDto;
import com.atiqur.bpdts.domain.model.Geolocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Slf4j
@Service
@RequiredArgsConstructor
public class BpdtsServiceClient {

    private static final String USERS_OF_A_CITY_PATH = "/city/{city}/users";
    private static final String ALL_USERS_PATH = "/users";

    private final RestTemplate restTemplate;
    private final Properties properties;
    private final DistanceCalculatorService distanceCalculatorService;
    private final Geolocation geolocationOfLondon;

    public Stream<UserResponseDto> getPeopleLivingIn(String city) {
        URI uri = fromUriString(properties.getBpdtsUrl() + USERS_OF_A_CITY_PATH).buildAndExpand(city).toUri();
        log.info("get all people living in {}", city);
        UserResponseDto[] userDtos = restTemplate.getForObject(uri, UserResponseDto[].class);

        return stream(userDtos);
    }

    public Stream<UserResponseDto> getPeopleWithCurrentCoordinateNearLondon() {
        properties.getBpdtsUrl();
        URI uri = fromUriString(properties.getBpdtsUrl() + ALL_USERS_PATH).buildAndExpand().toUri();
        log.info("get all people with current location: London");
        UserResponseDto[] userResponseDto = restTemplate.getForObject(uri, UserResponseDto[].class);

        return stream(userResponseDto)
                .filter(user -> distanceCalculatorService.distance(user, geolocationOfLondon) < 50.0);
    }
}

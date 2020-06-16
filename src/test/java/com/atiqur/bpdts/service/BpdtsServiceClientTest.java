package com.atiqur.bpdts.service;

import com.atiqur.bpdts.config.Properties;
import com.atiqur.bpdts.domain.UserResponseDto;
import com.atiqur.bpdts.domain.model.Geolocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BpdtsServiceClientTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Properties properties;
    @Mock
    private DistanceCalculatorService distanceCalculatorService;

    private Geolocation geolocationOfLondon = new Geolocation(51.507372, -0.127731d);

    private BpdtsServiceClient bpdtsServiceClient;

    @BeforeEach
    public void setup() {
        bpdtsServiceClient = new BpdtsServiceClient(restTemplate, properties,
                distanceCalculatorService, geolocationOfLondon);
    }

    @Test
    void testGetPeopleLivingIn() {
        given(properties.getBpdtsUrl()).willReturn("http://test.com");

        UserResponseDto expectedUser = new UserResponseDto();
        expectedUser.setId(12345);
        UserResponseDto[] UserResponseArray = {expectedUser};

        given(restTemplate.getForObject(any(URI.class), any(Class.class))).willReturn(UserResponseArray);

        assertThat(bpdtsServiceClient.getPeopleLivingIn("London").findAny().get())
                .isEqualTo(expectedUser);
    }
}
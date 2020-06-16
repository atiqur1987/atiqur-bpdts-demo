package com.atiqur.bpdts.config;

import com.atiqur.bpdts.domain.model.Geolocation;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public Geolocation geolocationOfLondon() {
        return new Geolocation(51.507372, -0.127731d);
    }
}

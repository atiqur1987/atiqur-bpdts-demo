package com.atiqur.bpdts.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties
@Validated
@Data
public class Properties {

    @NotNull(message = "bpdts-url property value is missing")
    private String bpdtsUrl;
}

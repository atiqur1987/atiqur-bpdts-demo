package com.atiqur.bpdts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Geolocation {

    private Double latitude;
    private Double longitude;
}

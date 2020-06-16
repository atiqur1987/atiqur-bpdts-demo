package com.atiqur.bpdts.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    private Integer id;
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String lastName;
    private String email;
    @JsonProperty(value = "ip_address")
    private String ipAddress;
    private Double latitude;
    private Double longitude;
    private String city;
}

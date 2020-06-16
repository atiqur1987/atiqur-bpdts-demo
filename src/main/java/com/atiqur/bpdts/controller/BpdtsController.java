package com.atiqur.bpdts.controller;

import com.atiqur.bpdts.domain.UserResponseDto;
import com.atiqur.bpdts.service.BpdtsServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
public class BpdtsController {

    @Autowired
    public BpdtsServiceClient bpdtsServiceClient;

    @GetMapping("/people-in-London")
    public List<UserResponseDto> peopleInLondon() {
        log.info("invoked endpoint /people-in-London");

        return Stream.concat(bpdtsServiceClient.getPeopleLivingIn("London"),
                bpdtsServiceClient.getPeopleWithCurrentCoordinateNearLondon())
                .collect(Collectors.toList());
    }

}
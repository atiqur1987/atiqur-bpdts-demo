package com.atiqur.bpdts.controller;

import com.atiqur.bpdts.domain.UserResponseDto;
import com.atiqur.bpdts.service.BpdtsServiceClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class BpdtsControllerTest {

    private final static String URI_PEOPLE_IN_LONDON = "/people-in-London";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BpdtsServiceClient bpdtsServiceClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void TestGetPeopleInLondon() throws Exception {

        UserResponseDto userResponseDto1 = createUserWithId(123);
        UserResponseDto userResponseDto2 = createUserWithId(456);
        List<UserResponseDto> expectedResponseDtos = of(userResponseDto1, userResponseDto2).collect(toList());

        given(bpdtsServiceClient.getPeopleLivingIn("London")).willReturn(of(userResponseDto1));
        given(bpdtsServiceClient.getPeopleWithCurrentCoordinateNearLondon()).willReturn(of(userResponseDto2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI_PEOPLE_IN_LONDON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        verify(bpdtsServiceClient, times(1)).getPeopleLivingIn("London");
        verify(bpdtsServiceClient, times(1)).getPeopleWithCurrentCoordinateNearLondon();

        TypeReference<List<UserResponseDto>> mapType = new TypeReference<List<UserResponseDto>>() {
        };
        assertThat(expectedResponseDtos, is(objectMapper.readValue(result.getResponse().getContentAsString(), mapType)));
    }

    private UserResponseDto createUserWithId(int id) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(id);

        return userResponseDto;
    }
}
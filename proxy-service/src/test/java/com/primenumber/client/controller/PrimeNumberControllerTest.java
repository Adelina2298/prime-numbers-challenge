package com.primenumber.client.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primenumber.client.exception.RestExceptionHandler;
import com.primenumber.client.service.PrimeNumberService;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PrimeNumberControllerTest {
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();
    @InjectMocks
    private PrimeNumberController primeNumberController;
    @Mock
    private PrimeNumberService primeNumberService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(primeNumberController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void getPrimeNumbers_should_returnCorrectData() throws Exception {
        List<Integer> primeNumbers = List.of(2, 3);
        given(primeNumberService.getPrimeNumbers(any())).willReturn(primeNumbers);

        MvcResult result = mockMvc.perform(get("/prime/3"))
                .andExpect(status().isOk())
                .andReturn();

        List<Integer> primeNumbersResult = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertEquals(primeNumbers, primeNumbersResult);
    }

    @Test
    public void getPrimeNumbers_should_throwInternalServerError_when_serviceThrowsStatusRuntimeException()
            throws Exception {
        given(primeNumberService.getPrimeNumbers(any())).willThrow(StatusRuntimeException.class);

        mockMvc.perform(get("/prime/3"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getPrimeNumbers_should_callServiceWithCorrectParameter() throws Exception {
        given(primeNumberService.getPrimeNumbers(eq(3))).willReturn(any());

        mockMvc.perform(get("/prime/3"))
                .andExpect(status().isOk());
    }

}

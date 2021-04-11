package com.primenumber.client.controller;

import com.primenumber.client.service.PrimeNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class PrimeNumberController {
    private final PrimeNumberService primeNumberService;

    public PrimeNumberController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @GetMapping(value = "/prime/{number}")
    public ResponseEntity<List<String>> getSupplierProduct(@NotNull @PathVariable Integer number) {
        List<String> response = primeNumberService.getPrimeNumbers(number);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

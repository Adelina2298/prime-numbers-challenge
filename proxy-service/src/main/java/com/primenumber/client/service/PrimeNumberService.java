package com.primenumber.client.service;

import com.google.common.collect.ImmutableList;
import com.primenumber.contracts.PrimeNumberRequest;
import com.primenumber.contracts.PrimeNumberResponse;
import com.primenumber.contracts.PrimeNumberServiceGrpc.PrimeNumberServiceBlockingStub;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrimeNumberService {
    private final PrimeNumberServiceBlockingStub blockingStub;

    public PrimeNumberService(PrimeNumberServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    public List<Integer> getPrimeNumbers(Integer number) {
        PrimeNumberRequest request = PrimeNumberRequest.newBuilder().setNumber(number).build();
        Iterator<PrimeNumberResponse> responseIterator = blockingStub.getPrimeNumbers(request);
        List<PrimeNumberResponse> responseList = ImmutableList.copyOf(responseIterator);
        return responseList.stream().map(PrimeNumberResponse::getNumber).collect(Collectors.toUnmodifiableList());
    }
}

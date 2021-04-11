package com.primenumber.server.service;

import com.primenumber.contracts.PrimeNumberRequest;
import com.primenumber.contracts.PrimeNumberResponse;
import com.primenumber.contracts.PrimeNumberServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;

public class PrimeNumberServiceImpl extends PrimeNumberServiceGrpc.PrimeNumberServiceImplBase {
    @Override
    public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> responseObserver) {
        int number = request.getNumber();
        boolean[] prime = getSieveOfEratosthenes(number);
        for (int i = 2; i <= number; i++) {
            if (prime[i]) {
                responseObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(i).build());
            }
        }
        responseObserver.onCompleted();
    }

    private boolean[] getSieveOfEratosthenes(int number) {
        boolean[] prime = new boolean[number + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= number; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= number; i += p) {
                    prime[i] = false;
                }
            }
        }
        return prime;
    }
}

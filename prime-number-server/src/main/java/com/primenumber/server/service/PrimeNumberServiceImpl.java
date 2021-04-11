package com.primenumber.server.service;

import com.primenumber.contracts.PrimeNumberRequest;
import com.primenumber.contracts.PrimeNumberResponse;
import com.primenumber.contracts.PrimeNumberServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;

public class PrimeNumberServiceImpl extends PrimeNumberServiceGrpc.PrimeNumberServiceImplBase {
    @Override
    public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> responseObserver) {
        int number = Integer.parseInt(request.getNumber());
        boolean[] prime = new boolean[number + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= number; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= number; i += p) {
                    prime[i] = false;
                }
            }
        }
        for (int i = 2; i <= number; i++) {
            if (prime[i]) {
                responseObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(String.valueOf(i)).build());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        responseObserver.onCompleted();
    }
}
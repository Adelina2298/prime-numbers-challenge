package com.primenumber.client.config;

import com.primenumber.contracts.PrimeNumberServiceGrpc;
import com.primenumber.contracts.PrimeNumberServiceGrpc.PrimeNumberServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    @Bean
    public ManagedChannel getManagedChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 8543).usePlaintext().build();
    }

    @Bean
    public PrimeNumberServiceBlockingStub getBlockingStub() {
        return PrimeNumberServiceGrpc.newBlockingStub(getManagedChannel());
    }
}

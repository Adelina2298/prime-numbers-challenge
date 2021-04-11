package com.primenumber.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.primenumber.server.service.PrimeNumberServiceImpl;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8543)
                .addService(new PrimeNumberServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}

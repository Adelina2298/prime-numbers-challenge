package com.primenumber.server;

import com.primenumber.contracts.PrimeNumberRequest;
import com.primenumber.contracts.PrimeNumberResponse;
import com.primenumber.contracts.PrimeNumberServiceGrpc;
import com.primenumber.server.service.PrimeNumberServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrpcServerTest {
    private final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();
    private ManagedChannel inProcessChannel;

    @BeforeEach
    public void setUp() throws Exception {
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).
                directExecutor().addService(new PrimeNumberServiceImpl()).build().start());
        inProcessChannel =
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build());
    }

    @Test
    public void getPrimeNumbers_should_returnCorrectValues() throws InterruptedException {
        PrimeNumberRequest request = PrimeNumberRequest.newBuilder().setNumber(3).build();
        PrimeNumberResponse response1 = PrimeNumberResponse.newBuilder().setNumber(2).build();
        PrimeNumberResponse response2 = PrimeNumberResponse.newBuilder().setNumber(3).build();
        List<PrimeNumberResponse> responseList = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<PrimeNumberResponse> responseObserver =
                new StreamObserver<>() {
                    @Override
                    public void onNext(PrimeNumberResponse value) {
                        responseList.add(value);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onCompleted() {
                        latch.countDown();
                    }
                };
        PrimeNumberServiceGrpc.PrimeNumberServiceStub stub = PrimeNumberServiceGrpc.newStub(inProcessChannel);

        stub.getPrimeNumbers(request, responseObserver);

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals(Arrays.asList(response1, response2), responseList);
    }

    @Test
    public void getPrimeNumbers_should_returnEmptyResult_when_noPrimeNumbersExist() throws InterruptedException {
        PrimeNumberRequest request = PrimeNumberRequest.newBuilder().setNumber(1).build();
        List<PrimeNumberResponse> responseList = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<PrimeNumberResponse> responseObserver =
                new StreamObserver<>() {
                    @Override
                    public void onNext(PrimeNumberResponse value) {
                        responseList.add(value);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onCompleted() {
                        latch.countDown();
                    }
                };
        PrimeNumberServiceGrpc.PrimeNumberServiceStub stub = PrimeNumberServiceGrpc.newStub(inProcessChannel);

        stub.getPrimeNumbers(request, responseObserver);

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(responseList.isEmpty());
    }

    @Test
    public void getPrimeNumbers_should_throwStatusRuntimeException_when_numberIsNegative() {
        PrimeNumberRequest request = PrimeNumberRequest.newBuilder().setNumber(-3).build();
        List<PrimeNumberResponse> responseList = new ArrayList<>();
        StreamObserver<PrimeNumberResponse> responseObserver =
                new StreamObserver<>() {
                    @Override
                    public void onNext(PrimeNumberResponse value) {
                        responseList.add(value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        assertTrue(t instanceof StatusRuntimeException);
                    }

                    @Override
                    public void onCompleted() {
                    }
                };
        PrimeNumberServiceGrpc.PrimeNumberServiceStub stub = PrimeNumberServiceGrpc.newStub(inProcessChannel);

        stub.getPrimeNumbers(request, responseObserver);
    }
}

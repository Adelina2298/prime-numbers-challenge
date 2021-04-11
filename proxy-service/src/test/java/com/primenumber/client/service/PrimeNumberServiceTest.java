package com.primenumber.client.service;

import com.primenumber.contracts.PrimeNumberRequest;
import com.primenumber.contracts.PrimeNumberResponse;
import com.primenumber.contracts.PrimeNumberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import io.grpc.util.MutableHandlerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrimeNumberServiceTest {
    private PrimeNumberService primeNumberService;
    private final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();
    private final MutableHandlerRegistry serviceRegistry = new MutableHandlerRegistry();

    @BeforeEach
    public void setUp() throws Exception {
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).
                directExecutor().fallbackHandlerRegistry(serviceRegistry).build().start());
        ManagedChannel channel = grpcCleanup.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());
        PrimeNumberServiceGrpc.PrimeNumberServiceBlockingStub blockingStub =
                PrimeNumberServiceGrpc.newBlockingStub(channel);
        primeNumberService = new PrimeNumberService(blockingStub);
    }

    @Test
    public void getPrimeNumbers_should_returnListOfPrimeNumbers() {
        PrimeNumberServiceGrpc.PrimeNumberServiceImplBase serviceImpl =
                mock(PrimeNumberServiceGrpc.PrimeNumberServiceImplBase.class, delegatesTo(
                        new PrimeNumberServiceGrpc.PrimeNumberServiceImplBase() {
                            @Override
                            public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> respObserver) {
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(2).build());
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(3).build());
                                respObserver.onCompleted();
                            }
                        }));
        serviceRegistry.addService(serviceImpl);

        List<Integer> primeNumbers = primeNumberService.getPrimeNumbers(3);

        assertEquals(2, primeNumbers.size());
        assertTrue(primeNumbers.contains(2));
        assertTrue(primeNumbers.contains(3));
    }

    @Test
    public void getPrimeNumbers_should_returnImmutableListOfValues() {
        PrimeNumberServiceGrpc.PrimeNumberServiceImplBase serviceImpl =
                mock(PrimeNumberServiceGrpc.PrimeNumberServiceImplBase.class, delegatesTo(
                        new PrimeNumberServiceGrpc.PrimeNumberServiceImplBase() {
                            @Override
                            public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> respObserver) {
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(2).build());
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(3).build());
                                respObserver.onCompleted();
                            }
                        }));
        serviceRegistry.addService(serviceImpl);

        List<Integer> primeNumbers = primeNumberService.getPrimeNumbers(3);

        assertThrows(UnsupportedOperationException.class, () -> primeNumbers.add(4));
    }

    @Test
    public void getPrimeNumbers_should_callServerWithCorrectValue() {
        PrimeNumberServiceGrpc.PrimeNumberServiceImplBase serviceImpl =
                mock(PrimeNumberServiceGrpc.PrimeNumberServiceImplBase.class, delegatesTo(
                        new PrimeNumberServiceGrpc.PrimeNumberServiceImplBase() {
                            @Override
                            public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> respObserver) {
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(2).build());
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(3).build());
                                respObserver.onCompleted();
                            }
                        }));
        serviceRegistry.addService(serviceImpl);
        ArgumentCaptor<PrimeNumberRequest> requestCaptor = ArgumentCaptor.forClass(PrimeNumberRequest.class);

        primeNumberService.getPrimeNumbers(3);

        verify(serviceImpl)
                .getPrimeNumbers(requestCaptor.capture(), ArgumentMatchers.any());
        assertEquals(3, requestCaptor.getValue().getNumber());
    }

    @Test
    public void getPrimeNumbers_should_returnEmptyList_when_noPrimeNumberReturned() {
        PrimeNumberServiceGrpc.PrimeNumberServiceImplBase serviceImpl =
                mock(PrimeNumberServiceGrpc.PrimeNumberServiceImplBase.class, delegatesTo(
                        new PrimeNumberServiceGrpc.PrimeNumberServiceImplBase() {
                            @Override
                            public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> respObserver) {
                                respObserver.onCompleted();
                            }
                        }));
        serviceRegistry.addService(serviceImpl);

        List<Integer> primeNumbers = primeNumberService.getPrimeNumbers(1);

        assertTrue(primeNumbers.isEmpty());
    }

    @Test
    public void getPrimeNumber_should_throwStatusRuntimeException_when_serverThrowsError() {
        StatusRuntimeException fakeError = new StatusRuntimeException(Status.FAILED_PRECONDITION);
        PrimeNumberServiceGrpc.PrimeNumberServiceImplBase serviceImpl =
                mock(PrimeNumberServiceGrpc.PrimeNumberServiceImplBase.class, delegatesTo(
                        new PrimeNumberServiceGrpc.PrimeNumberServiceImplBase() {
                            @Override
                            public void getPrimeNumbers(PrimeNumberRequest request, StreamObserver<PrimeNumberResponse> respObserver) {
                                respObserver.onNext(PrimeNumberResponse.newBuilder().setNumber(2).build());
                                respObserver.onError(fakeError);
                            }
                        }));
        serviceRegistry.addService(serviceImpl);

        assertThrows(StatusRuntimeException.class, () -> primeNumberService.getPrimeNumbers(1));
    }
}

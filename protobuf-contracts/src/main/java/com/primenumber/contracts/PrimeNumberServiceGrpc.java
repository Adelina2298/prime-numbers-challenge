package com.primenumber.contracts;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: PrimeNumberService.proto")
public final class PrimeNumberServiceGrpc {

  private PrimeNumberServiceGrpc() {}

  public static final String SERVICE_NAME = "PrimeNumberService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.primenumber.contracts.PrimeNumberRequest,
      com.primenumber.contracts.PrimeNumberResponse> METHOD_GET_PRIME_NUMBERS =
      io.grpc.MethodDescriptor.<com.primenumber.contracts.PrimeNumberRequest, com.primenumber.contracts.PrimeNumberResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "PrimeNumberService", "getPrimeNumbers"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.primenumber.contracts.PrimeNumberRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.primenumber.contracts.PrimeNumberResponse.getDefaultInstance()))
          .setSchemaDescriptor(new PrimeNumberServiceMethodDescriptorSupplier("getPrimeNumbers"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PrimeNumberServiceStub newStub(io.grpc.Channel channel) {
    return new PrimeNumberServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PrimeNumberServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PrimeNumberServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PrimeNumberServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PrimeNumberServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PrimeNumberServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPrimeNumbers(com.primenumber.contracts.PrimeNumberRequest request,
        io.grpc.stub.StreamObserver<com.primenumber.contracts.PrimeNumberResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PRIME_NUMBERS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_PRIME_NUMBERS,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.primenumber.contracts.PrimeNumberRequest,
                com.primenumber.contracts.PrimeNumberResponse>(
                  this, METHODID_GET_PRIME_NUMBERS)))
          .build();
    }
  }

  /**
   */
  public static final class PrimeNumberServiceStub extends io.grpc.stub.AbstractStub<PrimeNumberServiceStub> {
    private PrimeNumberServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PrimeNumberServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimeNumberServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PrimeNumberServiceStub(channel, callOptions);
    }

    /**
     */
    public void getPrimeNumbers(com.primenumber.contracts.PrimeNumberRequest request,
        io.grpc.stub.StreamObserver<com.primenumber.contracts.PrimeNumberResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_PRIME_NUMBERS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PrimeNumberServiceBlockingStub extends io.grpc.stub.AbstractStub<PrimeNumberServiceBlockingStub> {
    private PrimeNumberServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PrimeNumberServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimeNumberServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PrimeNumberServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.primenumber.contracts.PrimeNumberResponse> getPrimeNumbers(
        com.primenumber.contracts.PrimeNumberRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_PRIME_NUMBERS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PrimeNumberServiceFutureStub extends io.grpc.stub.AbstractStub<PrimeNumberServiceFutureStub> {
    private PrimeNumberServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PrimeNumberServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimeNumberServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PrimeNumberServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_PRIME_NUMBERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PrimeNumberServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PrimeNumberServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PRIME_NUMBERS:
          serviceImpl.getPrimeNumbers((com.primenumber.contracts.PrimeNumberRequest) request,
              (io.grpc.stub.StreamObserver<com.primenumber.contracts.PrimeNumberResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PrimeNumberServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PrimeNumberServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.primenumber.contracts.PrimeNumberServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PrimeNumberService");
    }
  }

  private static final class PrimeNumberServiceFileDescriptorSupplier
      extends PrimeNumberServiceBaseDescriptorSupplier {
    PrimeNumberServiceFileDescriptorSupplier() {}
  }

  private static final class PrimeNumberServiceMethodDescriptorSupplier
      extends PrimeNumberServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PrimeNumberServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PrimeNumberServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PrimeNumberServiceFileDescriptorSupplier())
              .addMethod(METHOD_GET_PRIME_NUMBERS)
              .build();
        }
      }
    }
    return result;
  }
}

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PrimeNumberService.proto

package com.primenumber.contracts;

public final class PrimeNumberServiceOuterClass {
  private PrimeNumberServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PrimeNumberRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PrimeNumberRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PrimeNumberResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PrimeNumberResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030PrimeNumberService.proto\"$\n\022PrimeNumbe" +
      "rRequest\022\016\n\006number\030\001 \001(\005\"%\n\023PrimeNumberR" +
      "esponse\022\016\n\006number\030\001 \001(\0052T\n\022PrimeNumberSe" +
      "rvice\022>\n\017getPrimeNumbers\022\023.PrimeNumberRe" +
      "quest\032\024.PrimeNumberResponse0\001B\035\n\031com.pri" +
      "menumber.contractsP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_PrimeNumberRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PrimeNumberRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrimeNumberRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_PrimeNumberResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_PrimeNumberResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PrimeNumberResponse_descriptor,
        new java.lang.String[] { "Number", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

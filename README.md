## prime-numbers-challenge
### Prerequisites 
* JAVA 11 or higher
* maven 3.6.0 

### Description

This repository contains the following services for serving prime numbers via REST call:
* `proxy-service` 
  
Exposes an HTTP endpoint responding to `GET /prime/<number>` that returns a list of prime numbers up to a given value.
It communicates with `prime-number-server` via gRPC and returns the data after the server finished streaming. 
  
In order to build and start the service, use `mvn clean install spring-boot:run`

* `prime-number-server`

Calculates the list of prime numbers and returns the values via a stream. 
The calculation is done using the Sieve of Eratosthenes algorithm.

In order to build and start the service, use `mvn clean install` and `java -jar target/prime-number-server-1.0-SNAPSHOT.jar`

* `protobuf-contracts`

Simple library that contains generated contracts from the proto file. Both the client and server use this library in order to communicate with the same contract.

For generating new contracts, use `mvn clean install`

### Additional comments
* for the connection between client and server I chose `gRPC`, as I found it easier to integrate and with a better documentation
* for error handling, both the client and server are covering the scenario where the given number is negative, in which case the API returns `BAD_REQUEST`. I chose to allow numbers that are lower than 2, even if the server won't return any prime number, but it can easily be changed. For other server exceptions, I didn't change the gRPC error handling as I found it explicit enough. Instead, the client is treating the exception and returns `INTERNAL_SERVER_ERROR` along with the error message.
* the requirement asking for a REST API that _continuously streams prime numbers_ confused me, as I couldn't follow if/how it's possible to have streaming integrated with REST, rather than WebSockets for example
* the 2 services contain only unit tests, but they could cover more scenarios with other type of tests, such as integration, component or contract tests
* currently, the client is calling the server for each call to generate prime numbers, but this could be improved by adding a cache on top of the REST API, given that the calculation doesn't change
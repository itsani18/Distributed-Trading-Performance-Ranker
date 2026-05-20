package com.example.sandboxservice.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class LoadGeneratorGrpcClient {

    @GrpcClient("load-generator-service")
    private LoadGeneratorServiceGrpc
            .LoadGeneratorServiceBlockingStub
            loadGeneratorStub;

    public void startBenchmark(
            String systemId,
            String endpoint,
            String containerId
    ) {

        StartBenchmarkRequest request =
                StartBenchmarkRequest.newBuilder()
                        .setSystemId(systemId)
                        .setEndpoint(endpoint)
                        .setContainerId(containerId)
                        .build();

        BenchmarkResponse response =
                loadGeneratorStub
                        .startBenchmark(request);

        System.out.println(
                response.getMessage()
        );
    }
}
package com.example.submissionservice.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class SandboxServiceGrpcClient {

    @GrpcClient("sandbox-service")
    private SandboxServiceGrpc.SandboxServiceBlockingStub sandboxStub;

    public DeployResponse deployApp(
            String systemId,
            String language,
            String filePath
    ) {

        DeployRequest request =
                DeployRequest.newBuilder()
                        .setSystemId(systemId)
                        .setLanguage(language)
                        .setFilePath(filePath)
                        .build();

        return sandboxStub.deploy(request);
    }
}
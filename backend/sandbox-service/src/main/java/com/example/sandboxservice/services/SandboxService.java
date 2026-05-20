package com.example.sandboxservice.services;

import com.example.submissionservice.grpc.DeployRequest;
import com.example.submissionservice.grpc.DeployResponse;
import com.example.sandboxservice.docker.DockerBuildManager;
import com.example.sandboxservice.docker.DockerHealthManager;
import com.example.sandboxservice.docker.DockerPortManager;
import com.example.sandboxservice.docker.DockerRunManager;
import com.example.sandboxservice.grpc.LoadGeneratorGrpcClient;
import org.springframework.stereotype.Service;

@Service
public class SandboxService {

    private final DockerBuildManager dockerBuildManager;

    private final DockerRunManager dockerRunManager;

    private final DockerHealthManager dockerHealthManager;

    private final DockerPortManager dockerPortManager;

    private final LoadGeneratorGrpcClient loadGeneratorGrpcClient;

    public SandboxService(
            DockerBuildManager dockerBuildManager,
            DockerRunManager dockerRunManager,
            DockerHealthManager dockerHealthManager,
            DockerPortManager dockerPortManager,
            LoadGeneratorGrpcClient loadGeneratorGrpcClient
    ) {
        this.dockerBuildManager = dockerBuildManager;
        this.dockerRunManager = dockerRunManager;
        this.dockerHealthManager = dockerHealthManager;
        this.dockerPortManager = dockerPortManager;
        this.loadGeneratorGrpcClient = loadGeneratorGrpcClient;
    }

    public DeployResponse deployApp(DeployRequest request) {

        String systemId = request.getSystemId();

        String language = request.getLanguage();

        String filePath = request.getFilePath();

        // allocate dynamic port
        int port = dockerPortManager.allocatePort();

        // build docker image
        String imageName =
                dockerBuildManager.buildImage(
                        systemId,
                        filePath
                );

        // run container
        String containerId =
                dockerRunManager.runContainer(
                        imageName,
                        port
                );

        // health check
        boolean healthy =
                dockerHealthManager.checkHealth(port);

        if (!healthy) {

            return DeployResponse.newBuilder()
                    .setSuccess(false)
                    .setStatus("FAILED")
                    .setMessage("Container health check failed")
                    .build();
        }

        // container endpoint
        String endpoint =
                "http://localhost:" + port;

        // trigger load generator
        loadGeneratorGrpcClient.startBenchmark(
                systemId,
                endpoint,
                containerId
        );

        // return success response
        return DeployResponse.newBuilder()
                .setSuccess(true)
                .setStatus("RUNNING")
                .setContainerId(containerId)
                .setEndpoint(endpoint)
                .setMessage("Deployment successful")
                .build();
    }
}
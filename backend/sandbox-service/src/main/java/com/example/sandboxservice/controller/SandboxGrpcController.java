package com.example.sandboxservice.controller;
import com.example.submissionservice.grpc.DeployRequest;
import com.example.submissionservice.grpc.DeployResponse;
import com.example.submissionservice.grpc.SandboxServiceGrpc;
import com.example.sandboxservice.services.SandboxService;

import io.grpc.stub.StreamObserver;

import org.springframework.stereotype.Component;

@Component
public class SandboxGrpcController
        extends SandboxServiceGrpc.SandboxServiceImplBase {

    private final SandboxService sandboxService;

    public SandboxGrpcController(
            SandboxService sandboxService
    ) {
        this.sandboxService = sandboxService;
    }

    @Override
    public void deploy(
            DeployRequest request,
            StreamObserver<DeployResponse> responseObserver
    ) {

        DeployResponse response =
                sandboxService.deployApp(request);

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
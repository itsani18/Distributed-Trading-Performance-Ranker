package com.example.sandboxservice.docker;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DockerStopManager {

    public void stopContainer(
            String containerId
    ) {

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "docker",
                            "stop",
                            containerId
                    );

            processBuilder.inheritIO();

            Process process =
                    processBuilder.start();

            int exitCode =
                    process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException(
                        "Docker stop failed"
                );
            }

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(e);
        }
    }
}
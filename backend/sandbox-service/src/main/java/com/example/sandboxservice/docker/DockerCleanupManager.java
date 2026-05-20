package com.example.sandboxservice.docker;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DockerCleanupManager {

    public void cleanupContainer(
            String containerId
    ) {

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "docker",
                            "rm",
                            "-f",
                            containerId
                    );

            processBuilder.inheritIO();

            Process process =
                    processBuilder.start();

            int exitCode =
                    process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException(
                        "Docker cleanup failed"
                );
            }

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(e);
        }
    }
}
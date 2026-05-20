package com.example.sandboxservice.docker;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DockerBuildManager {

    public String buildImage(
            String systemId,
            String projectPath
    ) {

        String imageName =
                "trading-image-" + systemId;

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "docker",
                            "build",
                            "-t",
                            imageName,
                            projectPath
                    );

            processBuilder.inheritIO();

            Process process =
                    processBuilder.start();

            int exitCode =
                    process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException(
                        "Docker build failed"
                );
            }

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(e);
        }

        return imageName;
    }
}
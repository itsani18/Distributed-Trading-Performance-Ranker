package com.example.sandboxservice.docker;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DockerRunManager {

    public String runContainer(
            String imageName,
            int port
    ) {

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "docker",
                            "run",
                            "-d",
                            "-p",
                            port + ":8080",
                            imageName
                    );

            Process process =
                    processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String containerId =
                    reader.readLine();

            int exitCode =
                    process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException(
                        "Docker run failed"
                );
            }

            return containerId;

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(e);
        }
    }
}
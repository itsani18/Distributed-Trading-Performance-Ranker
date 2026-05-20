package com.example.sandboxservice.docker;

import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class DockerHealthManager {

    public boolean checkHealth(int port) {

        try {

            URL url =
                    new URL(
                            "http://localhost:"
                                    + port
                                    + "/health"
                    );

            HttpURLConnection connection =
                    (HttpURLConnection)
                            url.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(3000);

            connection.setReadTimeout(3000);

            int responseCode =
                    connection.getResponseCode();

            return responseCode == 200;

        } catch (Exception e) {

            return false;
        }
    }
}
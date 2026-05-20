package com.example.sandboxservice.docker;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DockerPortManager {

    public int allocatePort() {

        Random random = new Random();

        return 8000 + random.nextInt(1000);
    }
}
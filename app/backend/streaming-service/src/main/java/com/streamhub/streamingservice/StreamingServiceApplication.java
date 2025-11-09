package com.streamhub.streamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.streamhub")
public class StreamingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamingServiceApplication.class, args);
    }
}

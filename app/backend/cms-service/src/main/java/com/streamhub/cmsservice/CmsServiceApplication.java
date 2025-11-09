package com.streamhub.cmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.streamhub")
public class CmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsServiceApplication.class, args);
    }
}

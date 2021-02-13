package com.n2s.quotesys1api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableDiscoveryClient
public class Quotesys1apiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Quotesys1apiApplication.class, args);
    }

}

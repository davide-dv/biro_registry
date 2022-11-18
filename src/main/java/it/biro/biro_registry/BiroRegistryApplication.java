package it.biro.biro_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BiroRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiroRegistryApplication.class, args);
    }
}
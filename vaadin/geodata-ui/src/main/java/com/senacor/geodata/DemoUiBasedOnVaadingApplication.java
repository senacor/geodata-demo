package com.senacor.geodata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class DemoUiBasedOnVaadingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoUiBasedOnVaadingApplication.class, args);
    }
}

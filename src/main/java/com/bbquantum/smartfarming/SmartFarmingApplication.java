package com.bbquantum.smartfarming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmartFarmingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartFarmingApplication.class, args);
    }

}

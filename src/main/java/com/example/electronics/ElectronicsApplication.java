package com.example.electronics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class ElectronicsApplication {

    public static void main(String[] args) {
        log.info("Application started");
        SpringApplication.run(ElectronicsApplication.class, args);
    }

}

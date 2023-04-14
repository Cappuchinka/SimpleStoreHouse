package ru.vsu.cs.simplestorehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.vsu.cs.simplestorehouse")
public class SimpleStoreHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleStoreHouseApplication.class, args);
    }

}

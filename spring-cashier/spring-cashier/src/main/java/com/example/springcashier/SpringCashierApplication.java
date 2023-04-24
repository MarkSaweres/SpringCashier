package com.example.springcashier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.springcashier"})
public class SpringCashierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCashierApplication.class, args);
    }

}

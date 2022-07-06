package com.example.demofetchtype;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoFetchTypeApplication {

    private final DemoService demoService;

    public static void main(String[] args) {

        SpringApplication.run(DemoFetchTypeApplication.class, args);
    }

    @PostConstruct
    void runDemo() {

        demoService.findTotalAmountForAllInvoice();
    }

}

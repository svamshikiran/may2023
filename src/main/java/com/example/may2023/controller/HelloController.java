package com.example.may2023.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") //name of the controller, used in URL Mapping
public class HelloController {

    private static Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/greet")
    public String sayHello(){
        log.info("INSIDE THE HELLO CONTROLLER");
        return "WELCOME TO THE SPRINGBOOT FRAMEWORK";
    }
}

// @RestController
// @Service
// @Repository
// @Component
// @Bean

// Inversion of Control = IOC pattern
// Dependency Injection

// Objects are called as BEANS in Spring framework

// Log Levels - DEBUG, INFO, WARN, ERROR & FATAL
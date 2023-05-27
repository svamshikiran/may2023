package com.example.may2023.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") //name of the controller, used in URL Mapping
public class HelloController {

    @GetMapping("/greet")
    public String sayHello(){
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

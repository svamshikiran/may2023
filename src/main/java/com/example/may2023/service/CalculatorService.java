package com.example.may2023.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double divide(double first, double second){
        System.out.println("INSIDE CALCULATOR SERVICE");
        return first/second;
    }
}

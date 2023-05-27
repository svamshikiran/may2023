package com.example.may2023.controller;

import com.example.may2023.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    @Autowired //Dependency Injection
    CalculatorService cService;

    @GetMapping("/divide/{firstValue}/{secondValue}")
    public ResponseEntity<Object> divide(
            @PathVariable("firstValue") double firstNumber,
            @PathVariable("secondValue") double secondNumber
    ){
        System.out.println("INSIDE CALCULATOR CONTROLLER");
        if(secondNumber == 0){
            return new ResponseEntity<>("SECOND VALUE IS ZERO, DIVISION OPERATION CANNOT BE PERFORMED", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cService.divide(firstNumber, secondNumber), HttpStatus.OK);
    }

    //Implement the remaining four operations - ADD, SUBTRACT, MULTIPLY and MODULUS
    //Simple Interest = principle * time * rate /100 (none of the elements should be zero)
    //Compound Interest
}

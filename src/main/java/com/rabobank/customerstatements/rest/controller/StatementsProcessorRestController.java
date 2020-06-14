package com.rabobank.customerstatements.rest.controller;

import com.rabobank.customerstatements.domain.ProcessorResponse;
import com.rabobank.customerstatements.domain.Transaction;
import com.rabobank.customerstatements.service.StatementsProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statementProcessor")
public class StatementsProcessorRestController {

    @Autowired
    private StatementsProcessorService statementsProcessorService;

    @PostMapping
    public ResponseEntity<ProcessorResponse> processor(@RequestBody List<Transaction> transactions){
        return new ResponseEntity<>(statementsProcessorService.process(transactions), HttpStatus.OK);
    }
}

package com.rabobank.customerstatements.rest.controller;

import com.rabobank.customerstatements.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
public class StatementsProcessorRestControllerTest {

    @Autowired
    StatementsProcessorRestController statementsProcessorRestController;

    //@Test (expected=HttpMessageNotReadableException.class)
    @Test
    void achieveCodeCoverage() {
        Transaction t1=new Transaction(1,"1", new BigDecimal(100.00),new BigDecimal(+200.00),"A",new BigDecimal(300.00));
        Transaction t2=new Transaction(2,"2", new BigDecimal(200.00),new BigDecimal(+300.00),"A",new BigDecimal(600.00));
        Transaction t3=new Transaction(3,"3", new BigDecimal(200.00),new BigDecimal(-100.00),"A",new BigDecimal(100.00));
        Transaction t4=new Transaction(3,"4", new BigDecimal(200.00),new BigDecimal(-100.00),"A",new BigDecimal(200.00));
        ArrayList<Transaction> ts=new ArrayList<Transaction>();
        ts.add(t1);
        ts.add(t2);
        ts.add(t3);
        ts.add(t4);
        ResponseEntity responseEntity=statementsProcessorRestController.processor(ts);
    }

}

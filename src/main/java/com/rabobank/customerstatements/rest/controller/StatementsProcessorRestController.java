package com.rabobank.customerstatements.rest.controller;

import com.rabobank.customerstatements.domain.ErrorRecord;
import com.rabobank.customerstatements.domain.ProcessorResponse;
import com.rabobank.customerstatements.domain.Record;
import com.rabobank.customerstatements.domain.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/statementProcessor")
public class StatementsProcessorRestController {

    @PostMapping
    public ResponseEntity<ProcessorResponse> processor(@RequestBody List<Transaction> transactions){
        ArrayList<Record> allRecords=new ArrayList<Record>();
        ArrayList<ErrorRecord> incorrectEndBalanceErrorRecords=new ArrayList<ErrorRecord>();
        ProcessorResponse processorResponse=new ProcessorResponse("SUCCESSFUL", new ArrayList<ErrorRecord>() );

        for(int i=0;i<transactions.size();i++){
            Transaction transaction=transactions.get(i);

            Record r = new Record(transaction.getTransactionReference(),transaction.getAccountNumber());
            allRecords.add(r);

            float mutationAmount=Float.parseFloat(transaction.getMutation().substring(1,transaction.getMutation().length()));
            String mutationSymbol=transaction.getMutation().substring(0,1);
            if(mutationSymbol.equalsIgnoreCase("+")){
                if(transaction.getStartBalance()+mutationAmount!=transaction.getEndBalance()){
                    incorrectEndBalanceErrorRecords.add(new ErrorRecord(transaction.getTransactionReference(),transaction.getAccountNumber()));
                }
            } else {
                if(transaction.getStartBalance()-mutationAmount!=transaction.getEndBalance()){
                    incorrectEndBalanceErrorRecords.add(new ErrorRecord(transaction.getTransactionReference(),transaction.getAccountNumber()));
                }
            }
        }

        final Set<Integer> set = new HashSet<>();
        ArrayList<ErrorRecord> duplicateReferenceErrorRecords=new ArrayList<ErrorRecord>();
        for(int i=0;i<allRecords.size();i++){
            if(!set.add(allRecords.get(i).getReference())){
                duplicateReferenceErrorRecords.add(new ErrorRecord(allRecords.get(i).getReference(),allRecords.get(i).getAccountNumber()));
            }
        }

        if(duplicateReferenceErrorRecords.size()>0){
            processorResponse.setResult("DUPLICATE_REFERENCE");
            processorResponse.setErrorRecords(duplicateReferenceErrorRecords);
        }

        if(incorrectEndBalanceErrorRecords.size()>0){
            processorResponse.setResult("INCORRECT_END_BALANCE");
            processorResponse.setErrorRecords(incorrectEndBalanceErrorRecords);
        }

        if(duplicateReferenceErrorRecords.size()>0&&incorrectEndBalanceErrorRecords.size()>0){
            processorResponse.setResult("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE");
            duplicateReferenceErrorRecords.addAll(incorrectEndBalanceErrorRecords);
            processorResponse.setErrorRecords(duplicateReferenceErrorRecords);
        }

        return new ResponseEntity<>(processorResponse, HttpStatus.OK);
    }
}

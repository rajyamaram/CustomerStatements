package com.rabobank.customerstatements.service;

import com.rabobank.customerstatements.domain.ErrorRecord;
import com.rabobank.customerstatements.domain.ProcessorResponse;
import com.rabobank.customerstatements.domain.Record;
import com.rabobank.customerstatements.domain.Transaction;
import com.rabobank.customerstatements.util.StatementsProcessorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementsProcessorServiceImpl implements StatementsProcessorService {
    @Override
    public ProcessorResponse process(List<Transaction> transactions) {
        ArrayList<Record> recordsToBeValidated=new ArrayList<Record>();
        ArrayList<ErrorRecord> incorrectEndBalanceErrorRecords=new ArrayList<>();
        ArrayList<ErrorRecord> duplicateReferenceErrorRecords=new ArrayList<>();

        for(int i=0;i<transactions.size();i++){
            Transaction transaction=transactions.get(i);
            Record r = new Record(transaction.getTransactionReference(),transaction.getAccountNumber());
            recordsToBeValidated.add(r);
            StatementsProcessorUtil.validateEndBalance(transaction,incorrectEndBalanceErrorRecords);
        }

        StatementsProcessorUtil.checkForDuplicates(recordsToBeValidated,duplicateReferenceErrorRecords);

        return StatementsProcessorUtil.constructResponse(duplicateReferenceErrorRecords,incorrectEndBalanceErrorRecords);
    }
}

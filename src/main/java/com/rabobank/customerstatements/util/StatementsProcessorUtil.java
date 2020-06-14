package com.rabobank.customerstatements.util;

import com.rabobank.customerstatements.domain.ErrorRecord;
import com.rabobank.customerstatements.domain.ProcessorResponse;
import com.rabobank.customerstatements.domain.Record;
import com.rabobank.customerstatements.domain.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatementsProcessorUtil {
    public static void validateEndBalance(Transaction transaction, List<ErrorRecord> incorrectEndBalanceErrorRecords){
        if(transaction.getStartBalance().add(transaction.getMutation()).compareTo(transaction.getEndBalance())!=0){
            incorrectEndBalanceErrorRecords.add(new ErrorRecord(transaction.getTransactionReference(),transaction.getAccountNumber()));
        }
    }

    public static void checkForDuplicates(List<Record> recordsToBeValidated, List<ErrorRecord> duplicateReferenceErrorRecords){
        final Set<Integer> set = new HashSet<>();
        for(int i=0;i<recordsToBeValidated.size();i++){
            if(!set.add(recordsToBeValidated.get(i).getReference())){
                duplicateReferenceErrorRecords.add(new ErrorRecord(recordsToBeValidated.get(i).getReference(),recordsToBeValidated.get(i).getAccountNumber()));
            }
        }
    }

    public static ProcessorResponse constructResponse(List<ErrorRecord> listOfDuplicates,List<ErrorRecord> listOfTransactionsWithIncorrectEndbalance){
        ProcessorResponse processorResponse=new ProcessorResponse("SUCCESSFUL", new ArrayList<ErrorRecord>() );
        if(listOfDuplicates.size()>0){
            processorResponse.setResult("DUPLICATE_REFERENCE");
            processorResponse.setErrorRecords(listOfDuplicates);
        }
        if(listOfTransactionsWithIncorrectEndbalance.size()>0){
            processorResponse.setResult("INCORRECT_END_BALANCE");
            processorResponse.setErrorRecords(listOfTransactionsWithIncorrectEndbalance);
        }
        if(listOfDuplicates.size()>0 && listOfTransactionsWithIncorrectEndbalance.size()>0){
            processorResponse.setResult("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE");
            listOfDuplicates.addAll(listOfTransactionsWithIncorrectEndbalance);
            processorResponse.setErrorRecords(listOfDuplicates);
        }
        return processorResponse;
    }
}

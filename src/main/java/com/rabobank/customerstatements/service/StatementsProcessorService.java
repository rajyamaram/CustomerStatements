package com.rabobank.customerstatements.service;

import com.rabobank.customerstatements.domain.ProcessorResponse;
import com.rabobank.customerstatements.domain.Transaction;

import java.util.List;

public interface StatementsProcessorService {
    ProcessorResponse process(List<Transaction> transactions);
}

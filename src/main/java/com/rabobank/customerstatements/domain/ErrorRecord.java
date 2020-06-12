package com.rabobank.customerstatements.domain;

public class ErrorRecord extends Record{
    public ErrorRecord(int reference, String accountNumber) {
        super(reference, accountNumber);
    }
}

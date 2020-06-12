package com.rabobank.customerstatements.domain;

public class Record {
    int reference;
    String accountNumber;

    public Record(int reference, String accountNumber) {
        this.reference = reference;
        this.accountNumber = accountNumber;
    }

    public int getReference() {
        return reference;
    }

    public String getAccountNumber() { return accountNumber;  }
}

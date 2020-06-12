package com.rabobank.customerstatements.domain;

public class Transaction {
    int transactionReference;
    String accountNumber;
    float startBalance;
    String mutation;
    String Description;
    float endBalance;

    public Transaction(int transactionReference, String accountNumber, float startBalance, String mutation, String description, float endBalance) {
        this.transactionReference = transactionReference;
        this.accountNumber = accountNumber;
        this.startBalance = startBalance;
        this.mutation = mutation;
        Description = description;
        this.endBalance = endBalance;
    }

    public int getTransactionReference() {
        return transactionReference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getStartBalance() {
        return startBalance;
    }

    public String getMutation() {
        return mutation;
    }

    public float getEndBalance() {
        return endBalance;
    }

}


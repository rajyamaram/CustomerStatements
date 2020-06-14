package com.rabobank.customerstatements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public class Transaction {
     @Getter int transactionReference;
     @Getter String accountNumber;
     @Getter BigDecimal startBalance;
     @Getter BigDecimal mutation;
     String Description;
     @Getter BigDecimal endBalance;
}


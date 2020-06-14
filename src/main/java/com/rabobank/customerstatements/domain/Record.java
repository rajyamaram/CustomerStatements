package com.rabobank.customerstatements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Record {
    @Getter int reference;
    @Getter String accountNumber;
}

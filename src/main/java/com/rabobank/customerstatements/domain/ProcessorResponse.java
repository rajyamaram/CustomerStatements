package com.rabobank.customerstatements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class ProcessorResponse {
    @Getter @Setter String result;
    @Getter @Setter List<ErrorRecord> errorRecords;
}

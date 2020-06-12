package com.rabobank.customerstatements.domain;

import java.util.List;

public class ProcessorResponse {
    String result;

    List<ErrorRecord> errorRecords;

    public ProcessorResponse(String result, List<ErrorRecord> errorRecords) {
        this.result = result;
        this.errorRecords = errorRecords;
    }

    public String getResult() { return result; }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ErrorRecord> getErrorRecords() { return errorRecords;  }

    public void setErrorRecords(List<ErrorRecord> errorRecords) {
        this.errorRecords = errorRecords;
    }

}

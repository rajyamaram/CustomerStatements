package com.rabobank.customerstatements;

import com.rabobank.customerstatements.domain.ErrorRecord;
import com.rabobank.customerstatements.domain.ProcessorResponse;
import com.rabobank.customerstatements.domain.Transaction;
import com.rabobank.customerstatements.rest.controller.StatementsProcessorRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StatementsProcessorApplicationTests {

	@Autowired
	StatementsProcessorRestController statementsProcessorRestController;

	@Test
	void contextLoads() {
		Transaction t1=new Transaction(1,"1", 100.00f,"+200.00","A",300.00f);
		Transaction t2=new Transaction(2,"2", 200.00f,"+300.00","A",600.00f);
		Transaction t3=new Transaction(1,"2", 200.00f,"-100.00","A",100.00f);
		Transaction t4=new Transaction(1,"2", 200.00f,"-100.00","A",200.00f);
		ArrayList<Transaction> ts=new ArrayList<Transaction>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		ts.add(t4);
		ResponseEntity responseEntity=statementsProcessorRestController.processor(ts);
		ProcessorResponse processorResponse= (ProcessorResponse) responseEntity.getBody();
		List<ErrorRecord> errorRecordsSet=processorResponse.getErrorRecords();
		errorRecordsSet.get(1).getAccountNumber();
		processorResponse.getResult();
	}

}

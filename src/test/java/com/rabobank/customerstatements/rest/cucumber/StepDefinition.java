package com.rabobank.customerstatements.rest.cucumber;

import com.rabobank.customerstatements.domain.Transaction;
import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StepDefinition {
    private static final String APPLICATION_JSON = "application/json";
    public RestTemplate restTemplate;
    static String responseJsonString;

    @When("^user uploads set of transaction records allTransactions (.*?) transactionsList (.*?)$")
    public void user_uploads_set_of_transaction_records(String arg1, @Transform(TransactionsListTransformer.class) List<Transaction> transactionsList) throws Throwable {
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(transactionsList, new HttpHeaders());
        restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/statementProcessor/", httpEntity, String.class);
        responseJsonString=responseEntity.getBody();
    }

    @Then("^the server should validate and send back response (.*?)$")
    public void the_server_should_validate_and_send_back(String validationString) throws Throwable {
        Assert.assertEquals(validationString, responseJsonString);
    }
}

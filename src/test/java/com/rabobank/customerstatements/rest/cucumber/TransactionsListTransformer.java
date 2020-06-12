package com.rabobank.customerstatements.rest.cucumber;

import com.rabobank.customerstatements.domain.Transaction;
import cucumber.api.Transformer;
import gherkin.deps.com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TransactionsListTransformer extends Transformer<List<Transaction>> {
    @Override
    public List<Transaction> transform(String value) {
        return new Gson().fromJson(value, ArrayList.class);
    }
}

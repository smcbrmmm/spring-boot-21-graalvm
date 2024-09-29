package com.smcbrmm.microservice.payment.Service;

import com.smcbrmm.microservice.payment.Model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransaction();

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findByPayerNameStartWith(String prefix);
}

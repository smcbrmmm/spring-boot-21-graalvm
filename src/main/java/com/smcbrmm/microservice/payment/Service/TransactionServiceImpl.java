package com.smcbrmm.microservice.payment.Service;

import com.smcbrmm.microservice.payment.Model.Transaction;
import com.smcbrmm.microservice.payment.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByPayerNameStartWith(String prefix) {
        return transactionRepository.findByPayerNameStartsWithIgnoreCase(prefix);
    }

//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    public TransactionServiceImpl(TransactionRepository transactionRepository){
//        this.transactionRepository  = transactionRepository;
//    }
//
//    @Override
//    public List<Transaction> getAllTransaction() {
//        return transactionRepository.findAll();
//    }
}

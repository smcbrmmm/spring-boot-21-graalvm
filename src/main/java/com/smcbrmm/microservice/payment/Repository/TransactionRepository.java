package com.smcbrmm.microservice.payment.Repository;

import com.smcbrmm.microservice.payment.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{

    public List<Transaction> findByPayerNameStartsWithIgnoreCase(String prefix);

    public long count();
}

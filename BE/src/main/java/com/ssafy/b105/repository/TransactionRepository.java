package com.ssafy.b105.repository;

import com.ssafy.b105.entity.blockchain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

}

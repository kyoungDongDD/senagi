package com.ssafy.b105.repository.blockchain;

import com.ssafy.b105.entity.blockchain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

}

package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.repository.blockchain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private final TransactionRepository repository;


  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }
}

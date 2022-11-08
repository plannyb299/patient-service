package com.example.patient.finance.transaction.repository;


import com.example.patient.finance.common.TransactionType;
import com.example.patient.finance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findByEmail(String email);

  List<Transaction> findByAccountNumber(Long customerId);

  List<Transaction> findByTransactionTypeAndEmail(TransactionType transactionType, String email);

}

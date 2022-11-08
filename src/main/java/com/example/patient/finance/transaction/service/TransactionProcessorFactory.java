package com.example.patient.finance.transaction.service;


import com.example.patient.finance.common.TransactionType;
import com.example.patient.finance.transaction.processors.DepositProcessor;
import com.example.patient.finance.transaction.processors.WithdrawalProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class TransactionProcessorFactory {

  public static TransactionStrategy getTransactionType(int transactionTypeId) {

    if (transactionTypeId == TransactionType.WITHDRAW.getTransactionTypeId()) {
      return new WithdrawalProcessor();
    } else if (transactionTypeId == TransactionType.DEPOSIT.getTransactionTypeId()) {
      return new DepositProcessor();
    } else {
      throw new IllegalArgumentException("Unknown TransactionType with ID: " + transactionTypeId);
    }

  }
}

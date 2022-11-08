package com.example.patient.finance.transaction.service;


import com.example.patient.finance.transaction.dto.CreateTransactionDto;
import com.example.patient.finance.transaction.dto.ViewTransactionDto;


public interface TransactionStrategy {

  ViewTransactionDto createTransaction(CreateTransactionDto transactionDto);
}

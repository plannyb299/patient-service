package com.example.patient.finance.transaction.service;


import com.example.patient.finance.common.TransactionType;
import com.example.patient.finance.transaction.dto.ViewTransactionDto;

import java.util.List;

public interface TransactionService {

  List<ViewTransactionDto> getTransactionHistoryByType(TransactionType transactionType, String email);

  List<ViewTransactionDto> getAllTransactions(String email);


}

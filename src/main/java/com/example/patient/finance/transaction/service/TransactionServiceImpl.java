package com.example.patient.finance.transaction.service;

import com.example.patient.finance.common.TransactionType;
import com.example.patient.finance.model.Transaction;
import com.example.patient.finance.transaction.dto.ViewTransactionDto;
import com.example.patient.finance.transaction.repository.TransactionRepository;
import com.example.patient.utils.CheckDetailsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  public TransactionServiceImpl(TransactionRepository transactionRepository) {

    this.transactionRepository = transactionRepository;
  }

  @Override
  public List<ViewTransactionDto> getTransactionHistoryByType(TransactionType transactionType, String email) {

    log.info("Get Transaction  history by transaction type :{}");

    List<Transaction> transactionList = transactionRepository.findByTransactionTypeAndEmail(transactionType, email);

    return createViewTransactionDto(transactionList);
  }


  @Override
  public List<ViewTransactionDto> getAllTransactions(String email) {

    log.info("Get Transaction  history for user email:{} " + email);

    List<Transaction> transactionList = transactionRepository.findByEmail(email);

    return createViewTransactionDto(transactionList);
  }

  public List<ViewTransactionDto> createViewTransactionDto(List<Transaction> transactionList) {

    if (transactionList.isEmpty()) {
      return new ArrayList<>();
    }
    return transactionList.stream().map(this::getViewTransactionDto).collect(Collectors.toList());
  }

  private ViewTransactionDto getViewTransactionDto(Transaction transaction) {

    return CheckDetailsUtil.getViewTransactionDto(transaction,
            CheckDetailsUtil.checkAccountInfo(transaction.getAccountNumber()));

  }

}

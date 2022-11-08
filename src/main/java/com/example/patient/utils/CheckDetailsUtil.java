package com.example.patient.utils;


import com.example.patient.dao.PatientDao;
import com.example.patient.exeption.BadRequestDataException;
import com.example.patient.finance.account.repository.AccountRepository;
import com.example.patient.finance.model.Account;
import com.example.patient.finance.model.Transaction;
import com.example.patient.finance.transaction.dto.CreateTransactionDto;
import com.example.patient.finance.transaction.dto.ViewTransactionDto;
import com.example.patient.finance.transaction.repository.TransactionRepository;
import com.example.patient.models.Patient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class CheckDetailsUtil {

  //To try to agree to Dry principles
  private static AccountRepository accountRepository;
  private static PatientDao appUserRepository;
  private static TransactionRepository transactionRepository;


  public static Account checkAccountInfo(String accountNumber) {

    Account account = accountRepository.findByAccountNumber(accountNumber);
    if (Objects.isNull(account)) {
      throw new BadRequestDataException("Account with this Account ID does not exist " + accountNumber);
    }

    return account;
  }

  public static Patient checkUser(String email) {

    Patient patient = appUserRepository.findByEmail(email);
    if (Objects.isNull(patient)) {
      throw new BadRequestDataException("User with this email does not exist " + email);
    }

    return patient;
  }


  public static ViewTransactionDto getViewTransactionDto(Transaction transaction, Account account) {
    ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
    viewTransactionDto.setAccount(account);
    viewTransactionDto.setAccountNumber(transaction.getAccountNumber());
    viewTransactionDto.setEmail(transaction.getEmail());
    viewTransactionDto.setPatient(CheckDetailsUtil.checkUser(transaction.getEmail()));


    viewTransactionDto.setAmount(transaction.getAmount());

    viewTransactionDto.setTransactionDateTime(transaction.getTransactionDate());
    viewTransactionDto.setTransactionId(transaction.getId());

    return viewTransactionDto;
  }

  public static Transaction getTransaction(CreateTransactionDto transactionDto, Account sourceAccount) {
    // create transaction if both accounts exist
    Transaction transaction = new Transaction();

    transaction.setTransactionDate(LocalDateTime.now());
    transaction.setEmail(transactionDto.getEmail());
    transaction.setAmount(transactionDto.getAmount());


    transaction.setAccountNumber(sourceAccount.getAccountNumber());
    return transaction;
  }

  public static Transaction saveTransaction(Transaction transaction) {
    // create transaction if both accounts exist
    log.info("Register Transaction :{}", transaction);
    return transactionRepository.save(transaction);

  }

  public static Account updateAccount(Account account) {
    // create transaction if both accounts exist
    log.info("Register Transaction :{}", account);
    return accountRepository.save(account);

  }


}

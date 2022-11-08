package com.example.patient.finance.transaction.processors;


import com.example.patient.finance.account.repository.AccountRepository;
import com.example.patient.finance.model.Account;
import com.example.patient.finance.model.Transaction;
import com.example.patient.finance.transaction.dto.CreateTransactionDto;
import com.example.patient.finance.transaction.dto.ViewTransactionDto;
import com.example.patient.finance.transaction.repository.TransactionRepository;
import com.example.patient.finance.transaction.service.TransactionStrategy;
import com.example.patient.utils.CheckDetailsUtil;

public class WithdrawalProcessor implements TransactionStrategy {

  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;

  public WithdrawalProcessor() {

  }

  public WithdrawalProcessor(AccountRepository accountRepository, TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;

  }

  @Override
  public ViewTransactionDto createTransaction(CreateTransactionDto transactionDto) {
    //check if account exists else throw exception
    Account sourceAccount = CheckDetailsUtil.checkAccountInfo(transactionDto.getAccountNumber());
    // create transaction
    Transaction transaction = CheckDetailsUtil.getTransaction(transactionDto, sourceAccount);

    //check for bank account withdrawal limits

    double sourceAccountBalance = sourceAccount.getAccountBalance();
    double debitedAccountBalance = sourceAccountBalance - transactionDto.getAmount();

    //we make it here we are ready to commit debit in the source account
    sourceAccount.setAccountBalance(debitedAccountBalance);
    CheckDetailsUtil.updateAccount(sourceAccount);
    //save transaction
    CheckDetailsUtil.saveTransaction(transaction);

    return CheckDetailsUtil.getViewTransactionDto(transaction, sourceAccount);
  }


}

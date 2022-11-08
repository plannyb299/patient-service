package com.example.patient.finance.account.service;


import com.example.patient.finance.account.dto.CreateAccountDto;
import com.example.patient.finance.account.dto.ViewAccountDto;
import com.example.patient.finance.model.Account;
import com.example.patient.models.Patient;

import java.util.List;

public interface AccountService {

  ViewAccountDto openAccount(CreateAccountDto createAccountDto);

  Account checkAccount(Patient patient);

  ViewAccountDto findAccountById(Long id);

  ViewAccountDto findByAccountNumber(String accountNumber);

  List<ViewAccountDto> findAllAccounts();
}

package com.example.patient.finance.account.dto;


import com.example.patient.finance.common.AccountType;
import com.example.patient.finance.common.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CreateAccountDto extends BaseDto implements Serializable {

  private AccountType accountType;
  private String accountNumber;
  private String email;
  private String currency;
  private double accountBalance;
  private LocalDate creationDate;
}

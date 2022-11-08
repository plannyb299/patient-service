package com.example.patient.finance.transaction.dto;


import com.example.patient.finance.common.BaseDto;
import com.example.patient.finance.common.TransactionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateTransactionDto extends BaseDto implements Serializable {


  private TransactionType transactionType;
  private double amount;
  private String accountNumber;

  private String email;

}

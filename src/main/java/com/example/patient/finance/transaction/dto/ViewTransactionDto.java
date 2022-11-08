package com.example.patient.finance.transaction.dto;


import com.example.patient.finance.model.Account;
import com.example.patient.models.Patient;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class ViewTransactionDto extends CreateTransactionDto implements Serializable {

  private Account account;
  private String email;
  private Patient patient;
  private LocalDateTime transactionDateTime;
  private Long transactionId;

}

package com.example.patient.finance.account.dto;


import com.example.patient.finance.common.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ViewAccountDto extends BaseDto implements Serializable {


  private String accountNumber;
  private String email;
  private String firstName;
  private String surname;
  private String currency;
  private double accountBalance;
  private LocalDate creationDate;
}

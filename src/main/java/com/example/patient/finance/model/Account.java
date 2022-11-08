package com.example.patient.finance.model;


import com.example.patient.finance.common.BaseEntity;
import com.example.patient.models.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts", indexes = {@Index(name = "indx_account", columnList = "id", unique = true)})
@Getter
@Setter
@ToString
public class Account extends BaseEntity {


  @Column(name = "accountNumber", nullable = false, unique = true, length = 45)
  private String accountNumber;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ACCOUNT"))
  @JsonIgnoreProperties
  @Transient
  private Patient patient;

//  @Column(name = "user_email", nullable = false, length = 45)
//  private String email;

  @Column(name = "currency", length = 5, unique = false, nullable = false)
  private String currency;

  @Column(name = "account_balance", unique = false, nullable = false)
  private double accountBalance;

  @Column(name = "creationDate", nullable = false)
  private LocalDate creationDate;

}

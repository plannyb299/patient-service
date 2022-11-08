package com.example.patient.finance.account.repository;


import com.example.patient.finance.model.Account;
import com.example.patient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Account findByAccountNumber(String accountId);

  Optional<Account> findById(Long Id);

  Account findByPatient(Patient patient);


}

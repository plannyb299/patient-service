package com.example.patient.finance.account;


import com.example.patient.exeption.BadRequestDataException;
import com.example.patient.finance.account.dto.CreateAccountDto;
import com.example.patient.finance.account.dto.ViewAccountDto;
import com.example.patient.finance.account.service.AccountService;
import com.example.patient.models.Patient;
import com.example.patient.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AccountsController {

  private final AccountService accountService;
  private final PatientService patientService;


  @SneakyThrows
  @PostMapping(value = "accounts/create")
  public ResponseEntity<ViewAccountDto> openAccount(@RequestBody CreateAccountDto accountDto) throws IOException {

    log.info("New Account Registration : {} ", accountDto);

    Patient patient = patientService.getPatientByEmail(accountDto.getEmail());
    if (!Objects.isNull(patient)) {
      final ViewAccountDto createAccountDto = accountService.openAccount(accountDto);
      return new ResponseEntity<>(createAccountDto, HttpStatus.CREATED);
    } else {
      throw new BadRequestDataException("There is no user with email " + accountDto.getEmail());
    }

  }

}

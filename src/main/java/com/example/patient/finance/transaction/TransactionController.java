package com.example.patient.finance.transaction;//package com.startaptradingapi.startaptradingapi.finance.transaction;

import com.example.patient.exeption.BadRequestDataException;
import com.example.patient.finance.common.TransactionType;
import com.example.patient.finance.transaction.dto.CreateTransactionDto;
import com.example.patient.finance.transaction.dto.ViewTransactionDto;
import com.example.patient.finance.transaction.service.TransactionProcessorFactory;
import com.example.patient.finance.transaction.service.TransactionService;
import com.example.patient.models.Patient;
import com.example.patient.service.PatientService;
import com.example.patient.utils.CheckDetailsUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final PatientService patientService;


    @SneakyThrows
    @PostMapping(value = "/transactions/{transactiontypeid}")

    public ResponseEntity<ViewTransactionDto> createTransaction(@RequestBody CreateTransactionDto createTransactionDto, @PathVariable("transactiontypeid") int transactionTypeId) throws IOException {

        log.info("New Transaction : {} ", createTransactionDto);

        //TODO check using security token, userid, or login key

        Optional<Patient> appUser = Optional.ofNullable(patientService.getPatientByEmail(createTransactionDto.getEmail()));
        if (appUser.isPresent()) {
            ViewTransactionDto viewTransactionDto = TransactionProcessorFactory.getTransactionType(transactionTypeId).createTransaction(createTransactionDto);
            return new ResponseEntity<>(viewTransactionDto, HttpStatus.CREATED);
        } else {
            throw new BadRequestDataException(" User does not exist");
        }

    }

    @GetMapping(value = "/transactions/{transactiontypeid}/{email}")
    public ResponseEntity<List<ViewTransactionDto>> getTransactionHistoryByType(@PathVariable("email") String email, @PathVariable("transactiontypeid") int transactionTypeId) throws IOException {

        log.info("Getting transaction of type :" + transactionTypeId + "{} for this user email" + email);

        //TODO check using security token, userid, or login key

        CheckDetailsUtil.checkUser(email);
        List<ViewTransactionDto> viewTransactionDtoList = transactionService.getTransactionHistoryByType(TransactionType.getTransactionType(transactionTypeId), email);
        return new ResponseEntity<>(viewTransactionDtoList, HttpStatus.CREATED);

    }

    @GetMapping(value = "/transactions/users/{email}")
    public ResponseEntity<List<ViewTransactionDto>> getAllTransactionHistory(@PathVariable("email") String email) throws IOException {

        log.info("Getting transaction history {} for this user" + email);

        //TODO check using security token, userid, or login key

        CheckDetailsUtil.checkUser(email);
        List<ViewTransactionDto> viewTransactionDtoList = transactionService.getAllTransactions(email);
        return new ResponseEntity<>(viewTransactionDtoList, HttpStatus.CREATED);

    }


}

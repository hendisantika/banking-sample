package id.my.hendisantika.bankingsample.controller;

import id.my.hendisantika.bankingsample.constants.constants;
import id.my.hendisantika.bankingsample.model.Account;
import id.my.hendisantika.bankingsample.service.AccountService;
import id.my.hendisantika.bankingsample.util.AccountInput;
import id.my.hendisantika.bankingsample.util.CreateAccountInput;
import id.my.hendisantika.bankingsample.util.InputValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 07.54
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(
            // TODO In the future support searching by card number in addition to sort code and account number
            @Valid @RequestBody AccountInput accountInput) {
        log.debug("Triggered AccountRestController.accountInput");

        // Validate input
        if (InputValidator.isSearchCriteriaValid(accountInput)) {
            // Attempt to retrieve the account information
            Account account = accountService.getAccount(
                    accountInput.getSortCode(), accountInput.getAccountNumber());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(constants.NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(
            @Valid @RequestBody CreateAccountInput createAccountInput) {
        log.debug("Triggered AccountRestController.createAccountInput");

        // Validate input
        if (InputValidator.isCreateAccountCriteriaValid(createAccountInput)) {
            // Attempt to retrieve the account information
            Account account = accountService.createAccount(
                    createAccountInput.getBankName(), createAccountInput.getOwnerName());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(constants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}

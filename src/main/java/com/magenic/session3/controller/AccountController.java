package com.magenic.session3.controller;

import com.magenic.session3.exception.RecordNotFoundException;
import com.magenic.session3.model.*;
import com.magenic.session3.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping()
    public ResponseEntity<Account> createAccount(
            @RequestBody AccountForm form
            ) {
        Account account;
        switch (form.getType()) {
            case "regular":
                account = new RegularAccount(form.getName());
                break;
            case "interest":
                account = new InterestAccount(form.getName());
                break;
            case "checking":
                account = new CheckingAcct(form.getName());
                break;
            default:
                account = null;
                break;
        }
        account = service.createOrUpdateAccount(account);

        return new ResponseEntity<Account>(account, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(
            @PathVariable long id
    ) throws RecordNotFoundException {
        Account account = service.getAccountById(id);

        return new ResponseEntity<Account>(account, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() throws RecordNotFoundException {
        List<Account> accounts = service.getAllAccounts();

        return new ResponseEntity<List<Account>>(accounts, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/{id}/transactions")
    public ResponseEntity<Account> transaction(
            @PathVariable long id,
            @RequestBody TransactionForm transactionForm
            ) throws RecordNotFoundException {
        Account account = service.getAccountById(id);
        if (transactionForm.getType().equals("withdraw")) {
            account.withdraw(transactionForm.getAmount());
        }
        if (transactionForm.getType().equals("deposit")) {
            account.deposit(transactionForm.getAmount());
        }
        service.createOrUpdateAccount(account);
        return new ResponseEntity<Account>(account, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteMapping(
            @PathVariable long id
    ) throws RecordNotFoundException {
        service.deleteAccount(id);
        return ResponseEntity.ok("Account successfully deleted");
    }


}

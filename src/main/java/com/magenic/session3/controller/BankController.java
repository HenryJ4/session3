package com.magenic.session3.controller;

import com.magenic.session3.models.AcctInput;
import com.magenic.session3.models.CheckingAcct;
import com.magenic.session3.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    AccountService service;

    @PostMapping
    public ResponseEntity<CheckingAcct> createOrUpdateUser(@RequestBody AcctInput input){
        CheckingAcct acct = service.create(input);
        return new ResponseEntity<CheckingAcct>(acct, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CheckingAcct>> getAllAccts() {
        List<CheckingAcct> list = service.getAllAccts();

        return new ResponseEntity<List<CheckingAcct>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/deposit")
    public ResponseEntity<CheckingAcct> depositToAcct(@RequestBody AcctInput input) {
        CheckingAcct acct = service.deposit(input);
            return new ResponseEntity<CheckingAcct>(acct,new HttpHeaders(), HttpStatus.OK);
        }

    @PutMapping("/withdraw")
    public ResponseEntity<CheckingAcct> withdrawToAcct(@RequestBody AcctInput input) {
        CheckingAcct acct = service.withdraw(input);
        return new ResponseEntity<CheckingAcct>(acct, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAcct(@PathVariable("id") Long id) {
        service.deleteAcct(id);
        return ResponseEntity.ok("Account is successfully deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckingAcct> getAcct(@PathVariable("id") Long id) {
        CheckingAcct acct = service.findAcct(id);
        return new ResponseEntity<CheckingAcct>(acct, new HttpHeaders(), HttpStatus.OK);
    }

}

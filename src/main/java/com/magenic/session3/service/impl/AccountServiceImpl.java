package com.magenic.session3.service.impl;

import com.magenic.session3.exception.RecordNotFoundException;
import com.magenic.session3.model.Account;
import com.magenic.session3.repository.AccountRepository;
import com.magenic.session3.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository repository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.repository = accountRepository;
    }

    @Override
    public Account createOrUpdateAccount(Account account) {
        return this.repository.save(account);
    }

    @Override
    public Account getAccountById(long id) throws RecordNotFoundException {
        Optional<Account> account = this.repository.findById(id);

        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RecordNotFoundException("No account exists for given ID");
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.repository.findAll();
    }

    @Override
    public void deleteAccount(long id) throws RecordNotFoundException {
        try {
            this.repository.deleteById(id);
        } catch (Exception ex) {
            throw new RecordNotFoundException("No account exists for given ID");
        }
    }

}

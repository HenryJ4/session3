package com.magenic.session3.service;

import com.magenic.session3.exception.RecordNotFoundException;
import com.magenic.session3.model.Account;
import com.magenic.session3.model.InterestAccount;
import com.magenic.session3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    public Account createOrUpdateAccount(Account account) {
        return this.repository.save(account);
    }

    public Account getAccountById(long id) throws RecordNotFoundException {
        Optional<Account> account = this.repository.findById(id);

        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RecordNotFoundException("No account exists for given ID");
        }
    }

    public List<Account> getAllAccounts() {
        return this.repository.findAll();
    }

    public void deleteAccount(long id) throws RecordNotFoundException {
        try {
            this.repository.deleteById(id);
        } catch (Exception ex) {
            throw new RecordNotFoundException("No account exists for given ID");
        }
    }
}

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
public interface AccountService {

    public void deleteAccount(long id) throws RecordNotFoundException;
    public List<Account> getAllAccounts();
    public Account getAccountById(long id) throws RecordNotFoundException;
    public Account createOrUpdateAccount(Account account);

}

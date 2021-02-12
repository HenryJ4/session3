package com.magenic.session3.service.impl;

import com.magenic.session3.models.AcctInput;
import com.magenic.session3.models.CheckingAcct;
import com.magenic.session3.repository.AccountRepository;
import com.magenic.session3.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public CheckingAcct create(AcctInput input) {
        CheckingAcct newAcct = new CheckingAcct();
        newAcct.setBalance(newAcct.getMinimumBal());
        newAcct.setName(input.getName());
        newAcct.setAcctNo(String.valueOf(ThreadLocalRandom.current().nextInt(0,999999999)));
        accountRepository.save(newAcct);
        return newAcct;
    }

    @Override
    public List<CheckingAcct> getAllAccts() {
        List<CheckingAcct> accts = accountRepository.findAll();
        if(!accts.isEmpty()){
            return accts;
        }
        return null;
    }

    @Override
    public CheckingAcct findAcct(Long id) {
        Optional<CheckingAcct> acct = accountRepository.findById(id);
        if(acct.isPresent()){
            return acct.get();
        }
        return null;
    }

    @Override
    public CheckingAcct deposit(AcctInput input) {
        Optional<CheckingAcct> acct = accountRepository.findById(input.getId());
        if(acct.isPresent()){
            CheckingAcct checkingAcct = acct.get();
            checkingAcct.setBalance(input.getAmt() + checkingAcct.getBalance() - checkingAcct.getTransactionCharge());
            accountRepository.save(checkingAcct);
            return checkingAcct;
        }
        return null;
    }

    @Override
    public CheckingAcct withdraw(AcctInput input) {
        Optional<CheckingAcct> acct = accountRepository.findById(input.getId());
        if(acct.isPresent()){
            CheckingAcct checkingAcct = acct.get();
            if(checkingAcct.getBalance() < checkingAcct.getMinimumBal()){
                checkingAcct.setBalance(checkingAcct.getBalance() - checkingAcct.getPenalty());
            }
            checkingAcct.setBalance(checkingAcct.getBalance() - input.getAmt()  - checkingAcct.getTransactionCharge());
            accountRepository.save(checkingAcct);
            return checkingAcct;
        }
        return null;
    }

    @Override
    public void deleteAcct(Long id) {
        Optional<CheckingAcct> acct = accountRepository.findById(id);
        if(acct.isPresent()){
            accountRepository.delete(acct.get());
        }
    }


}

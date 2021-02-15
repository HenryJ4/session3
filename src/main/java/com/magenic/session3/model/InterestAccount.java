package com.magenic.session3.model;

import javax.persistence.Entity;

@Entity
public class InterestAccount extends Account{
    private double interestRate;

    public InterestAccount(String name) {
        super(name, 0);
        this.interestRate = 0.03;
    }

    public InterestAccount() {
        super("", 0);
    }

    @Override
    protected void finishWithdraw() {}

    @Override
    protected void finishDeposit() {

    }
}

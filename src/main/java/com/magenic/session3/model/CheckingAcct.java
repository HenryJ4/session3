package com.magenic.session3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

@Entity
public class CheckingAcct extends Account {

    @JsonIgnore
    private double minimumBal = 100;

    @JsonIgnore
    private double penalty = 10;

    @JsonIgnore
    private double transactionCharge = 1;

    @JsonIgnore
    private double amt;

    public CheckingAcct() {
        super("", 100);
    }

    public CheckingAcct(String name) {
        super(name, 100);
    }

    public double getMinimumBal() {
        return minimumBal;
    }

    public void setMinimumBal(double minimumBal) {
        this.minimumBal = minimumBal;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(double transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    @Override
    protected void finishWithdraw() {
        if(super.balance < this.minimumBal){
            setBalance(super.balance - this.penalty - this.transactionCharge);
        }else{
            setBalance(super.balance - this.transactionCharge);
        }
    }

    @Override
    protected void finishDeposit() {
        setBalance(super.balance - this.transactionCharge);
    }
}

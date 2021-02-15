package com.magenic.session3.model;

import javax.persistence.Entity;

@Entity
public class RegularAccount extends Account{
	private double minimumBalance, penalty;
	
	public RegularAccount(String name) {
        super(name, 500);
        this.minimumBalance = 500;
        this.penalty = 10;
    }

    public RegularAccount() {
        super("", 500);
        this.minimumBalance = 500;
        this.penalty = 10;
    }
	
    @Override
    public void deposit(double amount) {
    	super.deposit(amount);
        if (minimumBalance > this.getBalance()) 
        	this.setBalance(this.getBalance() - penalty);
    }

    @Override
    public void withdraw(double amount) {
    	super.withdraw(amount);
    	if (minimumBalance > this.getBalance()) 
        	this.setBalance(this.getBalance() - penalty);
    }
    
	@Override
    protected void finishWithdraw() {}
}


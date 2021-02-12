package com.magenic.session3.model;

public class TransactionForm {
    private String type;
    private double amount;

    public TransactionForm() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

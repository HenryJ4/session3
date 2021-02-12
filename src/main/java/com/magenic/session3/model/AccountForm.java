package com.magenic.session3.model;

public class AccountForm {
    private String name;
    private String type;

    public AccountForm(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public AccountForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

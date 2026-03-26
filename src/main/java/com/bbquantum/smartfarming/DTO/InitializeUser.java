package com.bbquantum.smartfarming.DTO;

public class InitializeUser {
    private String emailAddress;

    private String initialPassword;

    public InitializeUser(String emailAddress, String initialPassword) {
        this.emailAddress = emailAddress;
        this.initialPassword = initialPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getInitialPassword() {
        return initialPassword;
    }
}

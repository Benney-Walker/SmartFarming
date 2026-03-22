package com.bbquantum.smartfarming.DTO;

public class LoginRequest {
    private String emailAddress;

    private String password;

    public LoginRequest(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}

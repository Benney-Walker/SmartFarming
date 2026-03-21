package com.bbquantum.smartfarming.DTO;

public class AddNewUser {
    private String userName;

    private String emailAddress;

    private String role;

    public AddNewUser(String userName, String emailAddress, String role) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getRole() {
        return role;
    }
}

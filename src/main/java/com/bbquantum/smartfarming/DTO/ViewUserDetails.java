package com.bbquantum.smartfarming.DTO;

public class ViewUserDetails {

    private String userId;

    private String userName;

    private String emailAddress;

    private String phoneNumber;

    private String role;

    private String status;

    public ViewUserDetails(String userId, String userName, String emailAddress, String phoneNumber, String role, String status) {
        this.userId = userId;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}

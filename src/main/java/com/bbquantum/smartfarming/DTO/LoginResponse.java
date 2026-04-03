package com.bbquantum.smartfarming.DTO;

public class LoginResponse {
    private String userName;

    private String role;

    private String token;

    public LoginResponse(String userName, String role, String token) {
        this.userName = userName;
        this.role = role;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}

package com.bbquantum.smartfarming.DTO;

import java.util.List;

public class LoginResponse {
    private String userName;

    private List<String> userRoles;

    private String token;

    public LoginResponse(String userName, List<String> userRoles, String token) {
        this.userName = userName;
        this.userRoles = userRoles;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public String getToken() {
        return token;
    }
}

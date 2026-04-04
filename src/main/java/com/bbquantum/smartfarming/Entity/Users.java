package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.UserStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Users {

    @Id
    private String userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false,  length = 100)
    private String emailAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(length = 100)
    private String password;

    private LocalDate dateOfRegistration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRoles> userRoles;

    @OneToMany(mappedBy = "user")
    private List<Fields> fields;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public Users(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String contact) {
        this.emailAddress = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRole) {
        this.userRoles = userRole;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}

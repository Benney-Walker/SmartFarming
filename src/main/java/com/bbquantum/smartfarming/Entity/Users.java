package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.UserRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Users {

    @Id
    private String userId;

    private String userName;

    private String contact;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDate dateOfRegistration;

    @OneToMany(mappedBy = "user")
    private List<Fields> fields;
}

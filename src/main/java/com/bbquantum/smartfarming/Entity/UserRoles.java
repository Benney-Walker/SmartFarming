package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.UserRole;
import jakarta.persistence.*;

@Entity
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "Users_userId")
    private Users user;

    @Enumerated(EnumType.STRING)
    private UserRole roles;

    public UserRoles() {}

    public UserRoles(Users user, UserRole roles) {
        this.user = user;
        this.roles = roles;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserRole getRoles() {
        return roles;
    }

    public void setRoles(UserRole roles) {
        this.roles = roles;
    }
}

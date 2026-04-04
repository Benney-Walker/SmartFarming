package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepo extends JpaRepository<UserRoles, Integer> {
    List<UserRoles> findByUser_UserId(String userId);
}

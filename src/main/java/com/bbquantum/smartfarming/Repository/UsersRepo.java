package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);

    Optional<Users> findByEmailAddress(String emailAddress);
}

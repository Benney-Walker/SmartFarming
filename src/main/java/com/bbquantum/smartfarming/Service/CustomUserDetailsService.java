package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    public CustomUserDetailsService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }
}

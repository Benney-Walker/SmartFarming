package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.UsersRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    public CustomUserDetailsService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        List<SimpleGrantedAuthority> authorities = user.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .toList();

        return new User(
                user.getEmailAddress(),
                user.getPassword(),
                authorities
        );
    }

    public Users getUserData(String emailAddress) throws UsernameNotFoundException {
        return usersRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}

package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Constants.UserRole;
import com.bbquantum.smartfarming.DTO.AddNewUser;
import com.bbquantum.smartfarming.DTO.LoginRequest;
import com.bbquantum.smartfarming.DTO.LoginResponse;
import com.bbquantum.smartfarming.Entity.UserRoles;
import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.UsersRepo;
import com.bbquantum.smartfarming.Utility.JwtUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UsersRepo usersRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtility jwtUtility;

    public UserService(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                       AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                       JwtUtility jwtUtility) {
        this.usersRepo = usersRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtility = jwtUtility;
    }

    public ResponseEntity<?> addNewUser(AddNewUser addNewUser) {
        String userName = addNewUser.getUserName();
        String emailAddress = addNewUser.getEmailAddress();
        String phoneNumber = addNewUser.getPhoneNumber();
        UserRole role = UserRole.valueOf(addNewUser.getRole());

        if (usersRepo.findByUserName(userName).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        if (usersRepo.findByEmailAddress(emailAddress).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email address already exists");
        }

        Users user = new Users();
        user.setUserName(userName);
        user.setEmailAddress(emailAddress);
        user.setPhoneNumber(phoneNumber);

        UserRoles newRole = new UserRoles();
        newRole.setUser(user);
        newRole.setRoles(role);

        List<UserRoles> userRoles = new ArrayList<>();
        userRoles.add(newRole);

        user.setUserRoles(userRoles);
        user.setDateOfRegistration(LocalDate.now());
        usersRepo.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> initializeUserPassword(String emailAddress, String password) {
        Users user = usersRepo.findByEmailAddress(emailAddress).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String storedPassword = user.getPassword();
        if (storedPassword != null && !storedPassword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "Password has already been set"
                    ));
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        usersRepo.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> authenticateUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = customUserDetailsService.getUserData(request.getUsername());
        List<String> rolesList = user.getUserRoles().stream().map(
                role -> role.getRoles().name()
        ).toList();

        String authToken = jwtUtility.generateJwtToken(user.getUserName(), rolesList);

        return ResponseEntity.ok(
                new LoginResponse(
                        user.getUserName(),
                        rolesList,
                        authToken
                )
        );
    }

    public ResponseEntity<?> checkUsers(String emailAddress) {
        Users user = usersRepo.findByEmailAddress(emailAddress).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "OLD_USER"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "status", "NEW_USER"
        ));
    }

    public Users getUser(String userId) {
        return usersRepo.findByUserId(userId).orElse(null);
    }
}

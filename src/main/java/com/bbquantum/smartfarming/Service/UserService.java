package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Constants.UserRole;
import com.bbquantum.smartfarming.Constants.UserStatus;
import com.bbquantum.smartfarming.DTO.*;
import com.bbquantum.smartfarming.Entity.UserRoles;
import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.UserRolesRepo;
import com.bbquantum.smartfarming.Repository.UsersRepo;
import com.bbquantum.smartfarming.Utility.IdGenerationUtil;
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

    private final IdGenerationUtil idGenerationUtil;

    private final UserRolesRepo userRolesRepo;

    public UserService(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                       AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                       JwtUtility jwtUtility,  IdGenerationUtil idGenerationUtil, UserRolesRepo userRolesRepo) {
        this.usersRepo = usersRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtility = jwtUtility;
        this.idGenerationUtil = idGenerationUtil;
        this.userRolesRepo = userRolesRepo;
    }

    public ResponseEntity<?> addNewUser(AddNewUser addNewUser) {
        String userName = addNewUser.getUserName();
        String emailAddress = addNewUser.getEmailAddress();
        String phoneNumber = addNewUser.getPhoneNumber();
        String role = addNewUser.getRole();

        if (usersRepo.findByUserName(userName).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        if (usersRepo.findByEmailAddress(emailAddress).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email address already exists");
        }

        Users user = new Users();
        user.setUserId(idGenerationUtil.generateEntityId("USER"));
        user.setUserName(userName);
        user.setEmailAddress(emailAddress);
        user.setPhoneNumber(phoneNumber);
        user.setDateOfRegistration(LocalDate.now());
        usersRepo.save(user);

        user.setUserRoles(addUserRoles(
                user,
                List.of(role)
        ));
        usersRepo.save(user);

        return ResponseEntity.ok().build();
    }

    private List<UserRoles> addUserRoles(Users user, List<String> roles) {
        List<UserRoles> userRoles = new ArrayList<>();
        for (String role : roles) {
            UserRoles userRole = new UserRoles(
                    user,
                    UserRole.valueOf(role)
            );
            userRolesRepo.save(userRole);
            userRoles.add(userRole);
        }

        return userRoles;
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
                role -> role.getUserRole().name()
        ).toList();

        String authToken = jwtUtility.generateJwtToken(user.getEmailAddress(), rolesList);

        return ResponseEntity.ok(Map.of(
                "username", user.getUserName(),
                "token", authToken,
                "role", rolesList.getFirst()
        ));
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

    public ResponseEntity<?> changeUserStatus(String userId, String status) {
        Users user = usersRepo.findByUserId(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        user.setUserStatus(UserStatus.valueOf(status));
        usersRepo.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> viewUserDetails(String userId) {
        Users user = usersRepo.findByUserId(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<String> roleList = user.getUserRoles().stream().map(
                role -> role.getUserRole().name()
        ).toList();

        ViewUserDetails userDetails = new ViewUserDetails(
                user.getUserId(),
                user.getUserName(),
                user.getEmailAddress(),
                user.getPhoneNumber(),
                roleList,
                user.getUserStatus().name()
        );

        return ResponseEntity.ok(userDetails);
    }

    public ResponseEntity<?> loadAllUsers() {
        List<Users> users = usersRepo.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "No users found"
            ));
        }

        return ResponseEntity.ok(
                users.stream().map(user -> {
                    String userId = user.getUserId();
                    String userName = user.getUserName();
                    String emailAddress = user.getEmailAddress();
                    String phoneNumber = user.getPhoneNumber();
                    List<String> userRoles = user.getUserRoles().stream().map(
                            role -> role.getUserRole().name()
                    ).toList();
                    String userStatus = user.getUserStatus().name();

                    return new ViewUserDetails(
                            userId,
                            userName,
                            emailAddress,
                            phoneNumber,
                            userRoles,
                            userStatus
                    );
                }).toList()
        );
    }

    public ResponseEntity<?> getTotalUsers() {
        return ResponseEntity.ok(Map.of(
                "totalUsers", usersRepo.count()
        ));
    }
}

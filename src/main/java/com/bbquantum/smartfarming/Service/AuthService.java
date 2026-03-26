package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Constants.UserRole;
import com.bbquantum.smartfarming.DTO.AddNewField;
import com.bbquantum.smartfarming.DTO.AddNewUser;
import com.bbquantum.smartfarming.DTO.LoginRequest;
import com.bbquantum.smartfarming.DTO.LoginResponse;
import com.bbquantum.smartfarming.Entity.Fields;
import com.bbquantum.smartfarming.Entity.UserRoles;
import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.FieldsRepo;
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

@Service
public class AuthService {

    private final UsersRepo usersRepo;

    private final FieldsRepo fieldsRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtility jwtUtility;

    public AuthService(UsersRepo usersRepo, FieldsRepo fieldsRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                       AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                       JwtUtility jwtUtility) {
        this.usersRepo = usersRepo;
        this.fieldsRepo = fieldsRepo;
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

    public ResponseEntity<?> addNewField(AddNewField addNewField) {
        String fieldName = addNewField.getFieldName();
        String fieldLocation = addNewField.getFieldLocation();
        String userName = addNewField.getUserName();

        Users user = usersRepo.findByUserName(userName).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (fieldsRepo.findByFieldName(fieldName).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Field already exists");
        }

        Fields field = new Fields();
        field.setFieldName(fieldName);
        field.setFieldLocation(fieldLocation);
        field.setUser(user);
        fieldsRepo.save(field);

        List<Fields> userFields = user.getFields();
        if (userFields == null) {
            userFields = new ArrayList<>();
        }
        userFields.add(field);
        usersRepo.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> initializeUserPassword(String emailAddress, String password) {
        Users user = usersRepo.findByEmailAddress(emailAddress).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String storedPassword = user.getPassword();
        if (storedPassword != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Password already set");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (user.getPassword() != null) {
            return ResponseEntity.ok("Existed");
        }

        return ResponseEntity.ok("new user");
    }
}

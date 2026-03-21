package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.Constants.UserRole;
import com.bbquantum.smartfarming.DTO.AddNewField;
import com.bbquantum.smartfarming.DTO.AddNewUser;
import com.bbquantum.smartfarming.Entity.Fields;
import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.FieldsRepo;
import com.bbquantum.smartfarming.Repository.UsersRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    private final UsersRepo usersRepo;

    private final FieldsRepo fieldsRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UsersRepo usersRepo, FieldsRepo fieldsRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepo = usersRepo;
        this.fieldsRepo = fieldsRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<?> addNewUser(AddNewUser addNewUser) {
        String userName = addNewUser.getUserName();
        String emailAddress = addNewUser.getEmailAddress();
        UserRole role = UserRole.valueOf(addNewUser.getRole());

        if (usersRepo.findByUserName(userName).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        if (usersRepo.findByEmailAddress(emailAddress).isPresent()) {
            return ResponseEntity.badRequest().body("Email address already exists");
        }

        Users user = new Users();
        user.setUserName(userName);
        user.setEmailAddress(emailAddress);
        user.setUserRole(role);
        usersRepo.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> addNewField(AddNewField addNewField) throws Exception {
        String fieldName = addNewField.getFieldName();
        String fieldLocation = addNewField.getFieldLocation();
        String userName = addNewField.getUserName();

        Users user = usersRepo.findByUserName(userName).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (fieldsRepo.findByFieldName(fieldName).isPresent()) {
            throw new Exception("Field name already exists");
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

    public String initializeUserPassword(String emailAddress, String password) throws Exception {
        Users user = usersRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new Exception("Email address not found"));

        String storedPassword = user.getPassword();
        if (storedPassword != null) {
            throw new Exception("Password already added, refresh page to login");
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        usersRepo.save(user);
        return "success";
    }
}

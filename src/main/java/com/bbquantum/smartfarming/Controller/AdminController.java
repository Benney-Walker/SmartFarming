package com.bbquantum.smartfarming.Controller;

import com.bbquantum.smartfarming.DTO.AddNewField;
import com.bbquantum.smartfarming.DTO.AddNewUser;
import com.bbquantum.smartfarming.Service.FieldService;
import com.bbquantum.smartfarming.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    private final FieldService fieldService;

    public AdminController(UserService userService, FieldService fieldService) {
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @PostMapping("/add-new-user")
    public ResponseEntity<?> addNewUser(@RequestBody AddNewUser addNewUser) {
        return userService.addNewUser(addNewUser);
    }

    @PostMapping("/v1/add-new-field")
    public ResponseEntity<?> addNewField(@RequestBody AddNewField addNewField) {
        return fieldService.addNewField(addNewField);
    }
}

package com.bbquantum.smartfarming.Controller;

import com.bbquantum.smartfarming.DTO.AddNewUser;
import com.bbquantum.smartfarming.DTO.InitializeUser;
import com.bbquantum.smartfarming.DTO.LoginRequest;
import com.bbquantum.smartfarming.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @GetMapping("/check-user")
    public ResponseEntity<?> checkUsers(@RequestParam String emailAddress) {
        return userService.checkUsers(emailAddress);
    }

    @PutMapping("/set-new-password")
    public ResponseEntity<?> initPassword(@RequestBody InitializeUser initializeUser) {
        return userService.initializeUserPassword(
                initializeUser.getEmailAddress(),
                initializeUser.getInitialPassword());
    }
}

package com.bbquantum.smartfarming.Controller;

import com.bbquantum.smartfarming.DTO.AddNewUser;
import com.bbquantum.smartfarming.DTO.InitializeUser;
import com.bbquantum.smartfarming.DTO.LoginRequest;
import com.bbquantum.smartfarming.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/add-new-user")
    public ResponseEntity<?> addNewUser(@RequestBody AddNewUser addNewUser) {
        return authService.addNewUser(addNewUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @GetMapping("/check-user")
    public ResponseEntity<?> checkUsers(@RequestParam String emailAddress) {
        return authService.checkUsers(emailAddress);
    }

    @PutMapping("/set-new-password")
    public ResponseEntity<?> initPassword(@RequestBody InitializeUser initializeUser) {
        return authService.initializeUserPassword(initializeUser.getEmailAddress(),
                initializeUser.getInitialPassword());
    }
}

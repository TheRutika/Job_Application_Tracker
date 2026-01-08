package com.Rutika.job_application_tracker.controllers;

import com.Rutika.job_application_tracker.dtos.LoginResponse;
import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.enums.Role;
import com.Rutika.job_application_tracker.repositories.UserRepository;
import com.Rutika.job_application_tracker.security.CustomUserDetails;
import com.Rutika.job_application_tracker.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // SIGNUP API
    // =========================
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody com.Rutika.job_application_tracker.dtos.SignupRequest request) {

        // 1️⃣ Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered");
        }

        // 2️⃣ Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // 🔐 Encode password (CRITICAL)
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 3️⃣ Assign default role
        user.setRole(Role.USER);

        // 4️⃣ Save user
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // =========================
    // LOGIN API
    // =========================
    @PostMapping("/login")
    public LoginResponse login(@RequestBody com.Rutika.job_application_tracker.dtos.LoginRequest request) {

        // 1️⃣ Authenticate (password check happens here)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2️⃣ Extract authenticated user
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        // 3️⃣ Generate JWT
        String token = jwtService.generateToken(user);

        // 4️⃣ Return token
        return new LoginResponse(token);
    }
}

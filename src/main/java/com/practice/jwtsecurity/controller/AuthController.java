package com.practice.jwtsecurity.controller;

import com.practice.jwtsecurity.model.User;
import com.practice.jwtsecurity.repository.UserRepository;
import com.practice.jwtsecurity.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Login and get token
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(loginRequest.getUsername());
            } else {
                return "Invalid login!";
            }
        } catch (AuthenticationException e) {
            return "Invalid username or password!";
        }
    }
}


///register → encrypts password and stores user in MongoDB.
//
///login → validates credentials via AuthenticationManager and returns a JWT token.
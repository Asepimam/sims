package com.sims.sims.Member.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sims.sims.Member.entities.User;
import com.sims.sims.Member.repositories.interfaces.ProfileRepository;
import com.sims.sims.Member.repositories.interfaces.UserRepository;
import com.sims.sims.Member.services.interfaces.AuthService;
import com.sims.sims.shared.dtos.UserCreateDto;
import com.sims.sims.shared.exception.*;
import com.sims.sims.shared.security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String register(UserCreateDto user) {
        // Validate email exists
        if (userRepository.existByEmail(user.getEmail() == null ? "" : user.getEmail())) {
            throw new BusinessException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // Create user
        User newUser = userRepository.createUser(user.getEmail(), user.getPassword());
        
        // Validasi newUser not null
        if (newUser == null) {
            throw new BusinessException("Failed to create user");
        }
        // Create profile
        profileRepository.createProfile(
            user.getFirstName(),
            user.getLastName(),
            newUser.getId(),
            " "
        );
        return "User created successfully";
    }

    @Override
    public Map<String, String> login(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null || !passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BusinessException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(existingUser.getEmail()); 
        String message = "Login successful";

    
        return Map.of(
            "token", token,
            "message", message
        );
    }


}

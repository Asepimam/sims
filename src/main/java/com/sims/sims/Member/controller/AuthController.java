package com.sims.sims.Member.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sims.sims.Member.services.interfaces.AuthService;
import com.sims.sims.shared.dtos.LoginDto;
import com.sims.sims.shared.dtos.ResponseDto;
import com.sims.sims.shared.dtos.UserCreateDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerMember(@Valid @RequestBody UserCreateDto userCreateDto) {
        String result = authService.register(userCreateDto);
        ResponseDto response = new ResponseDto(0, result, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginMember(@Valid @RequestBody LoginDto loginDto) {
        Map<String, String> result = authService.login(loginDto.getEmail(), loginDto.getPassword());
        ResponseDto response = new ResponseDto(0, result.get("message"), Map.of("token", result.get("token")));
        return ResponseEntity.ok(response);
    }
}
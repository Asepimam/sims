package com.sims.sims.Member.services.interfaces;

import java.util.Map;

import com.sims.sims.shared.dtos.UserCreateDto;

public interface AuthService {
    String register(UserCreateDto user);
    Map<String, String> login(String email, String password);
}

package com.sims.sims.Member.repositories.interfaces;

import com.sims.sims.Member.entities.User;

public interface UserRepository {
    User createUser(String email, String password);
    Boolean existByEmail(String email);
    User findByEmail(String email);
    Boolean updateUser(User user);
    Void deleteUser(Long id);
}

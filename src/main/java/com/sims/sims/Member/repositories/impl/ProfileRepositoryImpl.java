package com.sims.sims.Member.repositories.impl;

import com.sims.sims.Member.entities.Profile;
import com.sims.sims.Member.repositories.interfaces.ProfileRepository;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Profile createProfile(String firstName, String lastName, Long userId, String profileImageUrl) {
        String sql = """
            INSERT INTO profiles (first_name, last_name, profile_image_url, user_id)
            VALUES (?, ?, ?, ?) RETURNING *
            """;
        LocalDateTime now = LocalDateTime.now();
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new Profile(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("profile_image_url"),
                rs.getLong("user_id"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("updated_at", LocalDateTime.class)
            ), firstName, lastName, profileImageUrl, userId);
    }

    @Override
    public ProfileResponseWithEmailDto getProfileWithEmailById(Long userId) {
        String sql = """
            SELECT p.id, p.first_name, p.last_name, p.profile_image_url, p.user_id, p.created_at, p.updated_at, u.email
            FROM profiles p
            JOIN users u ON p.user_id = u.id
            WHERE p.user_id = ?
            """;

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
                new ProfileResponseWithEmailDto(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("profile_image_url"),
                    rs.getLong("user_id"),
                    rs.getString("email"),
                    rs.getObject("created_at", LocalDateTime.class),
                    rs.getObject("updated_at", LocalDateTime.class)
                ), userId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Profile not found for userId: " + userId);
        }
    }

    @Override
    public ProfileResponseWithEmailDto updateProfile(Long userId, Profile profile) {
        String sql = """
            UPDATE profiles
            SET first_name = ?, last_name = ?, updated_at = ?
            WHERE user_id = ?
            """;

        LocalDateTime now = LocalDateTime.now();
        int rows = jdbcTemplate.update(sql,
                profile.getFirstName(),
                profile.getLastName(),
                userId,
                now
        );

        if (rows > 0) {
            profile.setUpdatedAt(now);
            return getProfileWithEmailById(userId);
        }
        return null;
    }

    @Override
    public Void deleteProfile(Long id) {
        jdbcTemplate.update("DELETE FROM profiles WHERE id = ?", id);
        return null;
    }
}

package com.sims.sims.Member.repositories.interfaces;

import com.sims.sims.Member.entities.Profile;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;
import com.sims.sims.shared.dtos.UpdateProfileDto;

public interface ProfileRepository {
    Profile createProfile(String firstName, String lastName, Long userId, String profileImageUrl);
    ProfileResponseWithEmailDto getProfileWithEmailById(Long userId);
    ProfileResponseWithEmailDto updateProfile(Long userId, UpdateProfileDto profile);
    ProfileResponseWithEmailDto updateProfileImage(Long userId, String profileImageUrl);
    Void deleteProfile(Long id);
}

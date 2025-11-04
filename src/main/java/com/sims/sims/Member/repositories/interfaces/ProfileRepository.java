package com.sims.sims.Member.repositories.interfaces;

import com.sims.sims.Member.entities.Profile;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;

public interface ProfileRepository {
    Profile createProfile(String firstName, String lastName, Long userId, String profileImageUrl);
    ProfileResponseWithEmailDto getProfileWithEmailById(Long userId);
    ProfileResponseWithEmailDto updateProfile(Long userId, Profile profile);
    Void deleteProfile(Long id);
}

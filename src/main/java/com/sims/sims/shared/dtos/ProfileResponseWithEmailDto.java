package com.sims.sims.shared.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseWithEmailDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private Long userId;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

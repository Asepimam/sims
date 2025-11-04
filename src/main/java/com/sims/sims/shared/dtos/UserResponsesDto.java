package com.sims.sims.shared.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponsesDto {
    Long id;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

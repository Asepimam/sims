package com.sims.sims.InformationService.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    private Long id;
    private String bannerName;
    private String bannerImage;
    private String descriptions;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}

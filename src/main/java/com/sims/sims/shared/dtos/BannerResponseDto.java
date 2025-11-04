package com.sims.sims.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponseDto {
    private String bannerName;
    private String bannerImage;
    private String description;
}

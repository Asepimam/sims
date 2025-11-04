package com.sims.sims.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceSimsResponseDto {
    private String serviceName;
    private String serviceCode;
    private String serviceIcon;
    private Double serviceTarif;
}

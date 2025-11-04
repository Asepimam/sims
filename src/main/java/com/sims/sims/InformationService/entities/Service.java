package com.sims.sims.InformationService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private Long id;
    private String serviceName;
    private String serviceIcon;
    private String serviceCode;
    private Double serviceTarif;
    private String createdAt;
    private String updatedAt;
}

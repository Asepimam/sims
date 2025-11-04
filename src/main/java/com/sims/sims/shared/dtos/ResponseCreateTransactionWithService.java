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
public class ResponseCreateTransactionWithService {
    private String invoiceNumber;
    private String serviceCode;
    private String serviceName;
    private Double totalAmount;
    private LocalDateTime createdAt;
}

package com.sims.sims.shared.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ResponsesHistoryTransaction {
    private String invoiceNumber;
    private String transactionType;
    private String descriptions;
    private Double totalAmount;
    private LocalDateTime createAt;
}
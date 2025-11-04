package com.sims.sims.Transaction.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private String invoiceNumber;
    private Long userId;
    private String serviceCode;
    private String transactionType;
    private String descriptions;
    private Double totalAmount;
    private LocalDateTime createAt;
}

package com.sims.sims.Transaction.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private Long id;
    private String userId;
    private double balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

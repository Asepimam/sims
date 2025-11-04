package com.sims.sims.shared.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
public class UpdateAmountDto {
    @NotNull(message = "Top up amount must not be null")
    @DecimalMin(value = "1.0", inclusive = true, message = "Top up amount must be at least 1")
    private Double topUpAmount;

}
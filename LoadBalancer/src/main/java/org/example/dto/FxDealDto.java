package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FxDealDto {

    private String uniqueId;
    private String fromCurrency;
    private String toCurrency;
    private LocalDateTime timestamp;
    private double amount;
}


package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FxDeal {

    private String uniqueId;
    private String fromCurrency;
    private String toCurrency;
    private LocalDateTime timestamp;
    private double amount;
}


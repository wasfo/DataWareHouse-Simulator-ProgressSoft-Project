package org.example.dto;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FxDealDto {

    @NotBlank(message = "UniqueId cant be blank")
    @Size(min = 1, message = "UniqueId cannot be empty")
    private String uniqueId;

    @NotBlank(message = "FromCurrency cant be blank")
    @Size(min = 1, message = "fromCurrency cannot be empty")
    private String fromCurrency;

    @NotBlank(message = "ToCurrency cant be blank")
    @Size(min = 1, message = "toCurrency cannot be empty")
    private String toCurrency;

    @NotNull(message = "Timestamp cant be null")
    private LocalDateTime timestamp;

    @PositiveOrZero(message = "Amount should be greater than or equal to zero")
    private double amount;

}


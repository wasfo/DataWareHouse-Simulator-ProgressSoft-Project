package org.example.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fx_deals",
        indexes = {@Index(name = "idx_uniqueId",
                columnList = "uniqueId", unique = true)})
@Getter
@Setter
public class FXDeal {

    @Id
    private String uniqueId;
    @Column
    private String fromCurrency;
    @Column
    private String toCurrency;
    @Column
    private LocalDateTime timestamp;
    @Column
    private double amount;

}

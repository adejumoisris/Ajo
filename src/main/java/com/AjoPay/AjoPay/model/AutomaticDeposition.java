package com.AjoPay.AjoPay.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutomaticDeposition extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automaticDepositionId", nullable = false)
    private Long id;
    private Long accountId;
    private BigDecimal depositAmount;

}

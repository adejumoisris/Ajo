package com.AjoPay.AjoPay.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId", nullable = false)
    private Long id;

    private String accountNumber;
    private String bankName;
    private String accountType;
    private int bvn;
    private BigDecimal balance;
}

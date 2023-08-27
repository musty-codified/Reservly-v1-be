package com.mustycodified.Reservlyv1be.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "transaction_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2L;

    @Column(unique = true)
    private String reference;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String transactionType;
    private String customerEmail;
    private String channel;
    private String gateway_response;
    private String domain;

}

package com.mustycodified.Reservlyv1be.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "wallet_tbl")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@Builder
public class Wallet extends BaseEntity {
    private static final long serialVersionUID = 2L;

    @Column(unique = true)
    private String walletUUID;

    @OneToOne(mappedBy = "wallet", cascade = CascadeType.ALL)
    private User userDetails;

    @Column(nullable = false)
    private BigDecimal balance;


}


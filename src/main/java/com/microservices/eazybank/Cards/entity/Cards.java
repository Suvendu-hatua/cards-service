package com.microservices.eazybank.Cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cards extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "card_number",nullable = false)
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "available_amount")
    private int availableAmount;

    @Column(name = "total_amount")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

}

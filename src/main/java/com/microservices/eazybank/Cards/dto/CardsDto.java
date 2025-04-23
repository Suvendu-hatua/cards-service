package com.microservices.eazybank.Cards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class CardsDto {

    private String cardNumber;

    private String mobileNumber;

    private String cardType;

    private int availableAmount;

    private int totalLimit;

    private int amountUsed;
}

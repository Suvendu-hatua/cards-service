package com.microservices.eazybank.Cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(
        name = "Cards",
        description = "Schema to hold Cards Information"
)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {

    @Schema(
            description = "Card Number of the Customer",example = "100646930341"
    )
    @NotEmpty(message = "Card Number can not be a null or blank")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Mobile Number can not be a null or blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of the Customer",example = "9876543210"
    )
    private String mobileNumber;

    @NotEmpty(message = "CardType can not be a null or blank")
    @Schema(
            description = "Type of the card", example = "Credit Card"
    )
    private String cardType;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    private int availableAmount;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(
            description = "Total amount used by a Customer", example = "1000"
    )
    private int amountUsed;
}

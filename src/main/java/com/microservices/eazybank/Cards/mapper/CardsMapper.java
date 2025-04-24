package com.microservices.eazybank.Cards.mapper;

import com.microservices.eazybank.Cards.dto.CardsDto;
import com.microservices.eazybank.Cards.entity.Cards;

public class CardsMapper {

    /**
     * @param cards
     * @param cardsDto
     * @return
     */
    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber( cards.getCardNumber());
        cardsDto.setCardType( cards.getCardType() );
        cardsDto.setMobileNumber( cards.getMobileNumber() );
        cardsDto.setAvailableAmount( cards.getAvailableAmount() );
        cardsDto.setTotalLimit( cards.getTotalLimit() );
        cardsDto.setAmountUsed( cards.getAmountUsed() );
        return cardsDto;
    }

    /**
     * @param cardsDto
     * @param cards
     * @return
     */
    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        //setting properties
        cards.setCardNumber( cardsDto.getCardNumber());
        cards.setCardType( cardsDto.getCardType() );
        cards.setMobileNumber( cardsDto.getMobileNumber() );
        cards.setAvailableAmount( cardsDto.getAvailableAmount() );
        cards.setTotalLimit( cardsDto.getTotalLimit() );
        cards.setAmountUsed(cardsDto.getAmountUsed() );
        return cards;
    }
}

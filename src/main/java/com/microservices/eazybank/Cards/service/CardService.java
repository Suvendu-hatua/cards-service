package com.microservices.eazybank.Cards.service;

import com.microservices.eazybank.Cards.dto.CardsDto;

public interface CardService {
    void createCard(String mobileNumber);

    CardsDto fetchCardDetails(String mobileNumber);

    boolean updateCardDetails(CardsDto cardsDto) throws Exception;

    boolean deleteCardDetails(String mobileNumber);
}

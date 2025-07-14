package com.microservices.eazybank.Cards.service;

import com.microservices.eazybank.Cards.dto.CardsDto;
import com.microservices.eazybank.Cards.exceptionHandling.ResourceNotFoundException;

public interface CardService {
  void createCard(String mobileNumber);

  CardsDto fetchCardDetails(String mobileNumber);

  boolean updateCardDetails(CardsDto cardsDto) throws ResourceNotFoundException;

  boolean deleteCardDetails(String mobileNumber);
}

package com.microservices.eazybank.Cards.service.impl;

import com.microservices.eazybank.Cards.constants.CardsConstants;
import com.microservices.eazybank.Cards.dto.CardsDto;
import com.microservices.eazybank.Cards.entity.Cards;
import com.microservices.eazybank.Cards.exceptionHandling.CardAlreadyExistsException;
import com.microservices.eazybank.Cards.exceptionHandling.ResourceNotFoundException;
import com.microservices.eazybank.Cards.mapper.CardsMapper;
import com.microservices.eazybank.Cards.repository.CardsRepository;
import com.microservices.eazybank.Cards.service.CardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    private CardsRepository cardsRepository;

    @Autowired
    public CardServiceImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    /**
     * @param mobileNumber
     */
    @Override
    @Transactional
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with the given mobile number "+ mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    /**
     * @param cardsDto
     * @return
     */
    @Override
    @Transactional
    public boolean updateCardDetails(CardsDto cardsDto) throws Exception {
        Cards cards = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", cardsDto.getMobileNumber()));
        //validating Card Number ----- (Once it generated, can't be changed!)
        if(!cards.getCardNumber().equals(cardsDto.getCardNumber())) {
            throw new Exception("An exception occurred!\nCard number does not match");
        }
        //Updating amount
        if(cards.getAmountUsed() != cardsDto.getAmountUsed()) {
                int availableAmount = cardsDto.getTotalLimit() - cardsDto.getAmountUsed();
                cardsDto.setAvailableAmount(availableAmount);
        }
        cards=CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    @Transactional
    public boolean deleteCardDetails(String mobileNumber) {
        boolean isDeleted = false;
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        isDeleted = true;
        return isDeleted;
    }

    private  static Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 400000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        return newCard;
    }
}

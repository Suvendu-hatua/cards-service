package com.microservices.eazybank.Cards.repository;

import com.microservices.eazybank.Cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Cards, Long> {
}

package com.card.game.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.card.game.dtos.CardDTO;

public interface CardService {
    
    CardDTO createCard(CardDTO cardDTO);

    CardDTO getCardById(Long id);

    CardDTO getCardByTitle(String title);

    List<CardDTO> getAllCards(String title, Pageable page);

    void deleteCardById(Long id);
}

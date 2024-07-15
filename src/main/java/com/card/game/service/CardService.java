package com.card.game.service;

import java.util.List;

import com.card.game.dtos.CardDTO;

public interface CardService {
    
    CardDTO createCard(CardDTO cardDTO);

    CardDTO getCardById(Long id);

    CardDTO getCardByTitle(String title);

    List<CardDTO> getAllCards();

    void deleteCardById(Long id);
}

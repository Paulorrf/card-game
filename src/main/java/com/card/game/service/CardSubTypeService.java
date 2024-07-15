package com.card.game.service;

import java.util.List;

import com.card.game.dtos.CardSubTypeDTO;

public interface CardSubTypeService {
    CardSubTypeDTO createCardSubType(CardSubTypeDTO cardSubTypeDTO);

    CardSubTypeDTO getCardSubTypeById(Long id);

    CardSubTypeDTO getCardSubTypeByName(String name);

    List<CardSubTypeDTO> getAllCardSubType();

    void deleteCardSubTypeById(Long id);
}

package com.card.game.service;

import java.util.List;

import com.card.game.dtos.CardSubTypeDTO;

public interface CardSubTypeService {
    CardSubTypeDTO save(CardSubTypeDTO cardSubTypeDTO);

    CardSubTypeDTO findById(Long id);

    CardSubTypeDTO findByName(String name);

    List<CardSubTypeDTO> findAll();

    void deleteById(Long id);
}

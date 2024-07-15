package com.card.game.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.card.game.dtos.CardSubTypeDTO;
import com.card.game.mappers.CardSubTypeMapper;
import com.card.game.model.CardSubType;
import com.card.game.repository.CardSubTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardSubTypeServiceImpl implements CardSubTypeService {

    private final CardSubTypeRepository cardSubTypeRepository;
    private final CardSubTypeMapper cardSubTypeMapper;

    @Override
    public CardSubTypeDTO createCardSubType(CardSubTypeDTO cardSubTypeDTO) {
        try {
            CardSubType cardSubType = cardSubTypeRepository.save(cardSubTypeMapper.cardSubTypeDTOTocardSubType(cardSubTypeDTO));

            return cardSubTypeMapper.cardSubTypeToCardSubTypeDTO(cardSubType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to save CardSubType", e);
        }
    }

    @Override
    public CardSubTypeDTO getCardSubTypeById(Long id) {
            Optional<CardSubType> cardSubTypeOp = cardSubTypeRepository.findById(id);

            if(cardSubTypeOp.isPresent()) {
                return cardSubTypeMapper.cardSubTypeToCardSubTypeDTO(cardSubTypeOp.get());
            }

            throw new NoSuchElementException("CardSubType with id " + id + " not found");
    }

    @Override
    public CardSubTypeDTO getCardSubTypeByName(String name) {
        Optional<CardSubType> cardSubTypeOp = cardSubTypeRepository.findByName(name);

        if(cardSubTypeOp.isPresent()) {
            return cardSubTypeMapper.cardSubTypeToCardSubTypeDTO(cardSubTypeOp.get());
        }

        throw new NoSuchElementException("CardSubType with name " + name + " not found");
    }

    @Override
    public List<CardSubTypeDTO> getAllCardSubType() {
        try {
            List<CardSubType> cardSubTypes = cardSubTypeRepository.findAll();

            return cardSubTypeMapper.listCardSubTypeToCardSubTypeDTO(cardSubTypes);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while fetching card subtypes", e);
        }
    }

    @Override
    public void deleteCardSubTypeById(Long id) {
        try {
            cardSubTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting CardSubType with id " + id, e);
        }
    }

}

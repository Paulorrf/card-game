package com.card.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.card.game.dtos.CardSubTypeDTO;
import com.card.game.exceptions.CardSubTypeException;
import com.card.game.exceptions.DuplicateEntityException;
import com.card.game.mappers.CardSubTypeMapper;
import com.card.game.model.CardSubType;
import com.card.game.repository.CardSubTypeRepository;

import jakarta.persistence.EntityNotFoundException;
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
            throw new DuplicateEntityException("A cardSubType with the name " + cardSubTypeDTO.name() + "Already exist.", e);
        }
    }

    @Override
    public CardSubTypeDTO getCardSubTypeById(Long id) {
            return cardSubTypeRepository.findById(id)
                .map(cardSubTypeMapper::cardSubTypeToCardSubTypeDTO)
                .orElseThrow(() -> new EntityNotFoundException("CardSubType with id " + id + " not found"));
    }

    @Override
    public CardSubTypeDTO getCardSubTypeByName(String name) {
        return cardSubTypeRepository.findByName(name)
            .map(cardSubTypeMapper::cardSubTypeToCardSubTypeDTO)
            .orElseThrow(() -> new EntityNotFoundException("CardSubType with name " + name + " not found"));
    }

    @Override
    public List<CardSubTypeDTO> getAllCardSubType() {
        List<CardSubType> cardSubTypes = cardSubTypeRepository.findAll();

        return cardSubTypeMapper.listCardSubTypeToCardSubTypeDTO(cardSubTypes);
    }

    @Override
    public void deleteCardSubTypeById(Long id) {
        try {
            cardSubTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new CardSubTypeException("Error occurred while deleting CardSubType with id " + id, e);
        }
    }

}

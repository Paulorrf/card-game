package com.card.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.card.game.dtos.CardDTO;
import com.card.game.exceptions.CardDeletionException;
import com.card.game.exceptions.DuplicateEntityException;
import com.card.game.mappers.CardMapper;
import com.card.game.model.Card;
import com.card.game.repository.CardRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardDTO createCard(CardDTO cardDTO) {
        
        try {
            var card = cardRepository.save(cardMapper.cardDTOToCard(cardDTO));
            return cardMapper.cardToCardDTO(card);
            
        } catch (Exception e) {
            throw new DuplicateEntityException("A card with the title " + cardDTO.title() + "Already exist.", e);
        }
        
    }

    @Override
    public CardDTO getCardById(Long id) {
        return cardRepository.findById(id)
                .map(cardMapper::cardToCardDTO)
                .orElseThrow(() -> new EntityNotFoundException("Card with id " + id + " not found"));
    }

    @Override
    public CardDTO getCardByTitle(String title) {
        return cardRepository.findByTitle(title)
                .map(cardMapper::cardToCardDTO)
                .orElseThrow(() -> new EntityNotFoundException("Card with title " + title + " not found"));
    }

    @Override
    public List<CardDTO> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return cardMapper.listCardToCardDTO(cards);
    }

    @Override
    public void deleteCardById(Long id) {
        try {
            cardRepository.deleteById(id);
        } catch (Exception e) {
            throw new CardDeletionException("Error occurred while deleting Card with id " + id, e);
        }
    }
}

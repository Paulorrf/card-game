package com.card.game.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.card.game.dtos.CardDTO;
import com.card.game.model.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDTO cardToCardDTO(Card card);

    @Mapping(target = "id", ignore = true)
    Card cardDTOToCard(CardDTO cardDTO);

    List<CardDTO> listCardToCardDTO(List<Card> card);

    List<Card> listCardDTOToCard(List<CardDTO> cardDTO);

}

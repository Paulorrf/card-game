package com.card.game.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.card.game.dtos.CardSubTypeDTO;
import com.card.game.model.CardSubType;

@Mapper(componentModel = "spring")
public interface CardSubTypeMapper {
    CardSubTypeDTO cardSubTypeToCardSubTypeDTO(CardSubType cardSubType);

    @Mapping(target = "id", ignore = true)
    CardSubType cardSubTypeDTOTocardSubType(CardSubTypeDTO cardSubTypeDTO);

    List<CardSubTypeDTO> listCardSubTypeToCardSubTypeDTO(List<CardSubType> cardSubType);

    List<CardSubType> listCardSubTypeDTOTocardSubType(List<CardSubTypeDTO> cardSubTypeDTO);
}

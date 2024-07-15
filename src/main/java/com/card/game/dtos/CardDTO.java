package com.card.game.dtos;

import com.card.game.enums.CardTypeEnum;
import com.card.game.model.CardSubType;

public record CardDTO(String title, String image, String description, CardTypeEnum type, CardSubType subType) {

}

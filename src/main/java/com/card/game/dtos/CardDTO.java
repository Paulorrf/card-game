package com.card.game.dtos;

import com.card.game.model.CardSubType;
import com.card.game.model.CardType;

public record CardDTO(String title, 
                    String image, 
                    String description, 
                    CardType cardType, 
                    CardSubType subType) {

}

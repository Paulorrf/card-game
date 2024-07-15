package com.card.game.exceptions;

public class CardSubTypeException extends RuntimeException {

    public CardSubTypeException(String message) {
        super(message);
    }

    public CardSubTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}


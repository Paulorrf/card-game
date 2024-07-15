package com.card.game.exceptions;

public class CardDeletionException extends RuntimeException{

    public CardDeletionException(String message) {
        super(message);
    }

    public CardDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

}
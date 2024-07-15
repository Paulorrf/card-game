package com.card.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.card.game.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

    Optional<Card> findByTitle(String title);
}

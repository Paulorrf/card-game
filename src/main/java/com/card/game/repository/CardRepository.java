package com.card.game.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.card.game.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

    Optional<Card> findByTitle(String title);

    List<Card> findAllByTitle(String title, Pageable pageable);
}

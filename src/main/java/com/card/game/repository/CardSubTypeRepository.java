package com.card.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.card.game.model.CardSubType;

public interface CardSubTypeRepository extends JpaRepository<CardSubType, Long> {
    Optional<CardSubType> findByName(String name);
}

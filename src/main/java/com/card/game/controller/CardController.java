package com.card.game.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.card.game.dtos.CardDTO;
import com.card.game.service.CardService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {

    CardService cardService;

    @PostMapping()
    public ResponseEntity<CardDTO> createCard(CardDTO cardDTO) {
        var card = cardService.createCard(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) {
        var card = cardService.getCardById(id);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<CardDTO> getCardByTitle(@PathVariable String title) {
        var card = cardService.getCardByTitle(title);
        return ResponseEntity.ok(card);
    }

    @GetMapping()
    public ResponseEntity<List<CardDTO>> getAllCards(@RequestParam String title,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        var cards = cardService.getAllCards(title, pageable);
        return ResponseEntity.ok(cards);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardDTO> deleteById(@PathVariable Long id) {
        cardService.deleteCardById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.card.game.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.game.dtos.CardSubTypeDTO;
import com.card.game.service.CardSubTypeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/card-sub-types")
public class CardSubTypeController {

    private final CardSubTypeService cardSubTypeService;

    @PostMapping()
    public ResponseEntity<CardSubTypeDTO> createCardSubType(@RequestBody CardSubTypeDTO cardSubTypeDTO) {
        var cardSubType = cardSubTypeService.createCardSubType(cardSubTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardSubType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardSubTypeDTO> getCardSubTypeById(@PathVariable Long id) {
        var cardSubType = cardSubTypeService.getCardSubTypeById(id);
        return ResponseEntity.ok(cardSubType);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CardSubTypeDTO> getCardSubTypeByName(@PathVariable String name) {
        var cardSubType = cardSubTypeService.getCardSubTypeByName(name);
        return ResponseEntity.ok(cardSubType);
    }

    @GetMapping("/")
    public ResponseEntity<List<CardSubTypeDTO>> getAllCardSubTypes() {
        var cardSubType = cardSubTypeService.getAllCardSubType();
        return ResponseEntity.ok(cardSubType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardSubTypeById(@PathVariable Long id) {
        cardSubTypeService.deleteCardSubTypeById(id);
        return ResponseEntity.noContent().build();
    }
}


package com.card.game.controller;

import java.util.List;

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
    public CardSubTypeDTO createCardSubType(@RequestBody CardSubTypeDTO cardSubTypeDTO) {
        return cardSubTypeService.save(cardSubTypeDTO);
    }

    @GetMapping("/{id}")
    public CardSubTypeDTO getCardSubTypeById(@PathVariable Long id) {
        return cardSubTypeService.findById(id);
    }

    @GetMapping("/name/{name}")
    public CardSubTypeDTO getCardSubTypeByName(@PathVariable String name) {
        return cardSubTypeService.findByName(name);
    }

    @GetMapping("/")
    public List<CardSubTypeDTO> getAllCardSubTypes() {
        return cardSubTypeService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCardSubTypeById(@PathVariable Long id) {
        cardSubTypeService.deleteById(id);
    }
}


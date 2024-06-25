package com.lyw.api.app.core.card.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyw.api.app.core.card.application.services.CardCommandService;
import com.lyw.api.app.core.card.application.services.CardQueryService;
import com.lyw.api.app.core.card.domain.commands.CreateCardCommand;
import com.lyw.api.app.core.card.domain.commands.DeleteCardCommand;
import com.lyw.api.app.core.card.domain.commands.UpdateCardCommand;
import com.lyw.api.app.core.card.domain.queries.GetCardByIdQuery;
import com.lyw.api.app.core.card.domain.queries.GetCardsByUserIdQuery;
import com.lyw.api.app.core.card.infrastructure.dto.CardRequestDto;
import com.lyw.api.app.core.card.infrastructure.dto.CardResponseDto;
import com.lyw.api.app.core.card.infrastructure.mapper.CardMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/leadyourway/v1/cards")
@Tag(name = "Card Controller", description = "Card API")
@CrossOrigin
public class CardController {

    private final CardCommandService cardCommandService;
    private final CardQueryService cardQueryService;
    private final CardMapper cardMapper;

    public CardController(CardCommandService cardCommandService, CardQueryService cardQueryService,
            CardMapper cardMapper) {
        this.cardCommandService = cardCommandService;
        this.cardQueryService = cardQueryService;
        this.cardMapper = cardMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping("/{cardId}")
    @Operation(summary = "Requst a card")
    public ResponseEntity<CardResponseDto> getCardById(@PathVariable(name = "cardId") Long cardId) {
        return ResponseEntity.ok(cardMapper.toResponseDto(cardQueryService.handle(new GetCardByIdQuery(cardId))));
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/{userId}")
    @Operation(summary = "Requst user cards")
    public ResponseEntity<List<CardResponseDto>> getCardsByUserId(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(cardMapper.toResponseDto(cardQueryService.handle(new GetCardsByUserIdQuery(userId))));
    }

    @Transactional
    @PostMapping("/{userId}")
    @Operation(summary = "Create a card")
    public ResponseEntity<CardResponseDto> createCard(@PathVariable(name = "userId") String userId,
            @RequestBody CardRequestDto cardRequestDto) {
        return ResponseEntity
                .ok(cardMapper.toResponseDto(cardCommandService.handle(new CreateCardCommand(userId, cardRequestDto))));
    }

    @Transactional
    @PutMapping("/{cardId}")
    @Operation(summary = "Update a card")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable(name = "cardId") Long cardId,
            @RequestBody CardRequestDto cardRequestDto) {
        return ResponseEntity
                .ok(cardMapper.toResponseDto(cardCommandService.handle(new UpdateCardCommand(cardId, cardRequestDto))));
    }

    @Transactional
    @DeleteMapping("/{cardId}")
    @Operation(summary = "Delete a card")
    public ResponseEntity<String> deleteCard(@PathVariable(name = "cardId") Long cardId) {
        cardCommandService.handle(new DeleteCardCommand(cardId));
        return ResponseEntity.ok("Card deleted successfully");
    }
}

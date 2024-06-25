package com.lyw.api.app.core.card.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.lyw.api.app.core.card.domain.model.Card;
import com.lyw.api.app.core.card.infrastructure.dto.CardResponseDto;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mappings({
            @Mapping(target = "cardId", source = "id"),
            @Mapping(target = "cardNumber", source = "cardNumber"),
            @Mapping(target = "cardType", source = "cardType"),
            @Mapping(target = "cardCvv", source = "cardCvv"),
            @Mapping(target = "cardExpirationDate", source = "cardExpirationDate"),
            @Mapping(target = "cardAmount", source = "cardAmount"),
            @Mapping(target = "cardHolder", source = "cardHolder"),
            @Mapping(target = "cardMain", source = "cardMain")
    })
    CardResponseDto toResponseDto(Card card);

    List<CardResponseDto> toResponseDto(List<Card> cards);
}

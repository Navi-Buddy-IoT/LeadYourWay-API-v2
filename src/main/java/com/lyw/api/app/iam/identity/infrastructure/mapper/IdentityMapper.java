package com.lyw.api.app.iam.identity.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleResponseDto;
import com.lyw.api.app.core.card.domain.model.Card;
import com.lyw.api.app.core.card.infrastructure.dto.CardResponseDto;
import com.lyw.api.app.iam.identity.domain.model.User;
import com.lyw.api.app.iam.identity.infrastructure.dto.UserProfileResponseDto;
import com.lyw.api.app.iam.identity.infrastructure.dto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface IdentityMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "name", source = "entity.googleName"),
            @Mapping(target = "role", source = "entity.role.name"),
    })
    UserResponseDto userToResponseDto(User entity);

    @Mappings({
            @Mapping(target = "name", source = "entity.googleName"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "photoUrl", source = "entity.googlePhotoUrl"),
            @Mapping(target = "bicycles", source = "entity.bicycles", qualifiedByName = "mapBicycles"),
            @Mapping(target = "cards", source = "entity.cards", qualifiedByName = "mapCards"),
    })
    UserProfileResponseDto userToProfileResponseDto(User entity);

    default double mapTemperature(Temperature temperature) {
        return temperature.getTemperature();
    }

    default double mapVelocity(Velocity velocity) {
        return velocity.getVelocity();
    }

    @Named("mapBicycles")
    default List<BicycleResponseDto> mapBicycles(List<Bicycle> bicycles) {
        if (bicycles == null) {
            return null;
        }

        return bicycles.stream()
                .map(bicycle -> {
                    BicycleResponseDto dto = new BicycleResponseDto();
                    dto.setId(bicycle.getId());
                    dto.setBicycleName(bicycle.getBicycleName());
                    dto.setBicycleDescription(bicycle.getBicycleDescription());
                    dto.setBicyclePrice(bicycle.getBicyclePrice());
                    dto.setBicycleSize(bicycle.getBicycleSize());
                    dto.setBicycleModel(bicycle.getBicycleModel());
                    dto.setImageData(bicycle.getImageData());
                    dto.setTemperature(mapTemperature(bicycle.getTemperature()));
                    dto.setVelocity(mapVelocity(bicycle.getVelocity()));
                    return dto;
                })
                .toList();
    }

    @Named("mapCards")
    default List<CardResponseDto> mapCards(List<Card> cards) {
        if (cards == null) {
            return null;
        }

        return cards.stream()
                .map(card -> {
                    CardResponseDto dto = new CardResponseDto();
                    dto.setCardId(card.getId());
                    dto.setCardNumber(card.getCardNumber());
                    dto.setCardType(card.getCardType());
                    dto.setCardCvv(card.getCardCvv());
                    dto.setCardExpirationDate(card.getCardExpirationDate());
                    dto.setCardAmount(card.getCardAmount());
                    dto.setCardHolder(card.getCardHolder());
                    dto.setCardMain(card.getCardMain());
                    return dto;
                })
                .toList();
    }

}

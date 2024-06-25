package com.lyw.api.app.core.rent.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.card.domain.model.Card;
import com.lyw.api.app.core.rent.domain.model.Rent;
import com.lyw.api.app.core.rent.infrastructure.dto.RentResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentMapper {
    @Mappings({
            @Mapping(target = "rentStartDate", source = "rentStartDate"),
            @Mapping(target = "rentEndDate", source = "rentEndDate"),
            @Mapping(target = "rentPrice", source = "rentPrice"),
            @Mapping(target = "bicycleId", source = "bicycle", qualifiedByName = "mapBicycle"),
            @Mapping(target = "cardId", source = "card", qualifiedByName = "mapCard")
    })
    RentResponseDto toResponseDto(Rent rent);

    List<RentResponseDto> toResponseDto(List<Rent> rents);

    @Named("mapBicycle")
    default Long mapBicycle(Bicycle bicycle) {
        return bicycle.getId();
    }

    @Named("mapCard")
    default Long mapCard(Card card) {
        return card.getId();
    }
}

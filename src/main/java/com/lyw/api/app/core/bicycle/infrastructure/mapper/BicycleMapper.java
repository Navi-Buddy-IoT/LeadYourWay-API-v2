package com.lyw.api.app.core.bicycle.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.lyw.api.app.assets.domain.model.Gps;
import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BicycleMapper {
    @Mappings({
            @Mapping(target = "bicycleName", source = "bicycleName"),
            @Mapping(target = "bicycleDescription", source = "bicycleDescription"),
            @Mapping(target = "bicyclePrice", source = "bicyclePrice"),
            @Mapping(target = "bicycleSize", source = "bicycleSize"),
            @Mapping(target = "bicycleModel", source = "bicycleModel"),
            @Mapping(target = "imageData", source = "imageData"),
            @Mapping(target = "temperature", source = "temperature", qualifiedByName = "mapTemperature"),
            @Mapping(target = "velocity", source = "velocity", qualifiedByName = "mapVelocity"),
            @Mapping(target = "latitude", source = "gps", qualifiedByName = "mapGpsLatitude"),
            @Mapping(target = "longitude", source = "gps", qualifiedByName = "mapGpsLongitude")

    })
    BicycleResponseDto toResponseDto(Bicycle card);

    List<BicycleResponseDto> toResponseDto(List<Bicycle> cards);

    @Named("mapTemperature")
    default double mapTemperature(Temperature temperature) {
        return temperature.getTemperature();
    }

    @Named("mapVelocity")
    default double mapVelocity(Velocity velocity) {
        return velocity.getVelocity();
    }
    @Named("mapGpsLatitude")
    default double mapGpsLatitude(Gps gps) {
        return gps.getLatitude();
    }
    @Named("mapGpsLongitude")
    default double mapGpsLongitude(Gps gps) {
        return gps.getLongitude();
    }
}

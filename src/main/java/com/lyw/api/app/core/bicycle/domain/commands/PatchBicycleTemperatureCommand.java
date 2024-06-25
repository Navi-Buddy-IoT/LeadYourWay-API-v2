package com.lyw.api.app.core.bicycle.domain.commands;

import com.lyw.api.app.assets.infrastructure.dto.TemperatureRequestDto;

public record PatchBicycleTemperatureCommand(TemperatureRequestDto temperatureRequestDto) {
}

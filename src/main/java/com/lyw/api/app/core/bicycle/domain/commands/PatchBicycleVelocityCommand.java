package com.lyw.api.app.core.bicycle.domain.commands;

import com.lyw.api.app.assets.infrastructure.dto.VelocityRequestDto;

public record PatchBicycleVelocityCommand(VelocityRequestDto velocityRequestDto) {
}

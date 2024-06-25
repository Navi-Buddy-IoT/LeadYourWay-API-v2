package com.lyw.api.app.assets.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Velocity (request)")
public class VelocityRequestDto {
    @Schema(description = "Velocity")
    private double velocity;
    @Schema(description = "Bicycle Id")
    private Long bicycleId;
}

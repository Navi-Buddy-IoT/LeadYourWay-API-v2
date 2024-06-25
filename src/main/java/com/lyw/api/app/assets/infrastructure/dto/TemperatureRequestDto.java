package com.lyw.api.app.assets.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Temperature (Request)")
public class TemperatureRequestDto {
    @Schema(description = "Temperature")
    private double temperature;
    @Schema(description = "Bicycle Id")
    private Long bicycleId;
}

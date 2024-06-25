package com.lyw.api.app.assets.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Gps (request)")
public class GpsRequestDto {
    @Schema(description = "Latitude")
    private double latitude;
    @Schema(description = "Longitude")
    private double longitude;
    @Schema(description = "Bicycle Id")
    private Long bicycleId;
    
}

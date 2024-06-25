package com.lyw.api.app.iam.identity.infrastructure.dto;

import java.util.List;

import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleResponseDto;
import com.lyw.api.app.core.card.infrastructure.dto.CardResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "User (Request)")
public class UserProfileResponseDto {
    @Schema(description = "User Name")
    private String name;
    @Schema(description = "User Email")
    private String email;
    @Schema(description = "User Photo URL")
    private String photoUrl;
    @Schema(description = "User Bicycles")
    private List<BicycleResponseDto> bicycles;
    @Schema(description = "User Cards")
    private List<CardResponseDto> cards;
}

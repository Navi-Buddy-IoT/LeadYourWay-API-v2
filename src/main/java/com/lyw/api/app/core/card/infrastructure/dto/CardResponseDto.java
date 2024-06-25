package com.lyw.api.app.core.card.infrastructure.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Card (Response)")
public class CardResponseDto {
    @Schema(description = "cardId")
    private Long cardId;
    @Schema(description = "cardNumber")
    private String cardNumber;
    @Schema(description = "cardType")
    private String cardType;
    @Schema(description = "cardCvv")
    private String cardCvv;
    @Schema(description = "cardExpirationDate")
    private LocalDate cardExpirationDate;
    @Schema(description = "cardAmount")
    private Double cardAmount;
    @Schema(description = "cardHolder")
    private String cardHolder;
    @Schema(description = "cardMain")
    private boolean cardMain;
}

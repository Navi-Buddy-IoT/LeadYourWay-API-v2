package com.lyw.api.app.assets.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Login (Request)")
public class LoginRequestDto {
    @Schema(description = "Email")
    private String email;
    @Schema(description = "Password")
    private String password;
}

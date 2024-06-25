package com.lyw.api.app.iam.identity.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.lyw.api.app.iam.identity.application.services.UserCommandService;
import com.lyw.api.app.iam.identity.application.services.UserQueryService;
import com.lyw.api.app.iam.identity.domain.commands.PatchUserCommand;
import com.lyw.api.app.iam.identity.domain.model.User;
import com.lyw.api.app.iam.identity.domain.queries.GetUserByIdQuery;
import com.lyw.api.app.iam.identity.infrastructure.dto.UserProfileResponseDto;
import com.lyw.api.app.iam.identity.infrastructure.dto.UserRequestDto;
import com.lyw.api.app.iam.identity.infrastructure.dto.UserResponseDto;
import com.lyw.api.app.iam.identity.infrastructure.mapper.IdentityMapper;
import com.lyw.api.app.iam.identity.infrastructure.transformation.RegisterUserBikerCommandFromRequestDtoAssembler;
import com.lyw.api.app.iam.identity.infrastructure.transformation.RegisterUserRenterCommandFromRequestDtoAssembler;
import com.lyw.api.app.shared.constants.HeaderConstants;

import static com.lyw.api.app.shared.constants.ConfigurationMessages.USER_CREATED;

@RestController
@RequestMapping("/api/leadyourway/v1/users")
@Tag(name = "User Controller")
@CrossOrigin
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final IdentityMapper identityMapper;

    public UserController(
            UserCommandService userCommandService,
            UserQueryService userQueryService,
            IdentityMapper identityMapper) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.identityMapper = identityMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user", description = "Allows to retrieve a user.")
    public UserProfileResponseDto getUser(@Parameter @PathVariable("id") String id) {
        User user = userQueryService.handle(new GetUserByIdQuery(id));
        return identityMapper.userToProfileResponseDto(user);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user", description = "Allows to update a user.")
    public UserResponseDto updateUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userCommandService.handle(new PatchUserCommand(userRequestDto));
        return identityMapper.userToResponseDto(user);
    }

    @GetMapping("/login/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user", description = "Allows to retrieve a user.")
    public UserResponseDto loginUser(@Parameter @PathVariable("id") String id) {
        User user = userQueryService.handle(new GetUserByIdQuery(id));
        return identityMapper.userToResponseDto(user);
    }

    @PostMapping("/register/biker")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register user", description = "Allows to register a user.")
    public UserResponseDto registerTourist(@RequestBody @Valid UserRequestDto userRequestDto,
            HttpServletResponse response) {
        User user = userCommandService
                .handle(RegisterUserBikerCommandFromRequestDtoAssembler.toCommandFromDto(userRequestDto));
        response.setHeader(HeaderConstants.MESSAGES, USER_CREATED);
        return identityMapper.userToResponseDto(user);
    }

    @PostMapping("/register/renter")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register user", description = "Allows to register a user.")
    public UserResponseDto registerAgency(@RequestBody @Valid UserRequestDto userRequestDto,
            HttpServletResponse response) {
        User user = userCommandService
                .handle(RegisterUserRenterCommandFromRequestDtoAssembler.toCommandFromDto(userRequestDto));
        response.setHeader(HeaderConstants.MESSAGES, USER_CREATED);
        return identityMapper.userToResponseDto(user);
    }
}

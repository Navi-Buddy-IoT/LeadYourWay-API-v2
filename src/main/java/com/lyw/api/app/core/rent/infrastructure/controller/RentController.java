package com.lyw.api.app.core.rent.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleResponseDto;
import com.lyw.api.app.core.bicycle.infrastructure.mapper.BicycleMapper;
import com.lyw.api.app.core.rent.application.services.RentCommandService;
import com.lyw.api.app.core.rent.application.services.RentQueryService;
import com.lyw.api.app.core.rent.domain.commands.CreateRentCommand;
import com.lyw.api.app.core.rent.domain.quieries.GetBicyclesByRentCardUserIdQuery;
import com.lyw.api.app.core.rent.infrastructure.dto.RentRequestDto;
import com.lyw.api.app.core.rent.infrastructure.dto.RentResponseDto;
import com.lyw.api.app.core.rent.infrastructure.mapper.RentMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/leadyourway/v1/rents")
@Tag(name = "Rent Controller", description = "Rent API")
@CrossOrigin
public class RentController {

    private final RentCommandService rentCommandService;
    private final RentQueryService rentQueryService;
    private final RentMapper rentMapper;
    private final BicycleMapper bicycleMapper;

    public RentController(RentCommandService rentCommandService, RentMapper rentMapper,
            RentQueryService rentQueryService, BicycleMapper bicycleMapper) {
        this.rentCommandService = rentCommandService;
        this.rentQueryService = rentQueryService;
        this.rentMapper = rentMapper;
        this.bicycleMapper = bicycleMapper;
    }

    @Transactional
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all bicycles rented by a user")
    public ResponseEntity<List<BicycleResponseDto>> getRentsByCardUserId(@PathVariable(name = "userId") String userId) {
        return ResponseEntity
                .ok(bicycleMapper.toResponseDto(rentQueryService.handle(new GetBicyclesByRentCardUserIdQuery(userId))));
    }

    @Transactional
    @PostMapping
    @Operation(summary = "Create a rent")
    public ResponseEntity<RentResponseDto> createRent(@RequestBody RentRequestDto rentRequestDto) {
        return ResponseEntity
                .ok(rentMapper.toResponseDto(rentCommandService.handle(new CreateRentCommand(rentRequestDto))));
    }
}

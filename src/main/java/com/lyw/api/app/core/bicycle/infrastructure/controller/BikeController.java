package com.lyw.api.app.core.bicycle.infrastructure.controller;

import java.time.LocalDate;
import java.util.List;

import com.lyw.api.app.assets.infrastructure.dto.VelocityRequestDto;
import com.lyw.api.app.core.bicycle.domain.commands.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.lyw.api.app.assets.infrastructure.dto.GpsRequestDto;
import com.lyw.api.app.assets.infrastructure.dto.TemperatureRequestDto;
import com.lyw.api.app.core.bicycle.application.services.BicycleCommandService;
import com.lyw.api.app.core.bicycle.application.services.BicycleQueryService;
import com.lyw.api.app.core.bicycle.domain.queries.GetAvailableBicyclesQuery;
import com.lyw.api.app.core.bicycle.domain.queries.GetBicycleByIdQuery;
import com.lyw.api.app.core.bicycle.domain.queries.GetBicyclesQuery;
import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleRequestDto;
import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleResponseDto;
import com.lyw.api.app.core.bicycle.infrastructure.mapper.BicycleMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/leadyourway/v1/bicycles")
@Tag(name = "Bicycle Controller", description = "Bicycles API")
@CrossOrigin
public class BikeController {

    private final BicycleQueryService bicycleQueryService;
    private final BicycleCommandService bicycleCommandService;
    private final BicycleMapper bicycleMapper;

    public BikeController(BicycleQueryService bicycleQueryService, BicycleCommandService bicycleCommandService,
            BicycleMapper bicycleMapper) {
        this.bicycleQueryService = bicycleQueryService;
        this.bicycleCommandService = bicycleCommandService;
        this.bicycleMapper = bicycleMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping("/{bicycleId}")
    @Operation(summary = "Get a bicycle by id")
    public ResponseEntity<BicycleResponseDto> getBicycleById(@PathVariable(name = "bicycleId") Long bicycleId) {
        return ResponseEntity
                .ok(bicycleMapper.toResponseDto(bicycleQueryService.handle(new GetBicycleByIdQuery(bicycleId))));
    }

    @Transactional(readOnly = true)
    @GetMapping("")
    @Operation(summary = "Get all bicycles")
    public ResponseEntity<List<BicycleResponseDto>> getAllBicycles() {
        return ResponseEntity.ok(bicycleMapper.toResponseDto(bicycleQueryService.handle(new GetBicyclesQuery())));
    }

    @Transactional(readOnly = true)
    @GetMapping("/available")
    @Operation(summary = "Get all available bicycles")
    public ResponseEntity<List<BicycleResponseDto>> getAvailableBicycles(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate starDate,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(bicycleMapper
                .toResponseDto(bicycleQueryService.handle(new GetAvailableBicyclesQuery(starDate, endDate))));
    }

    @Transactional
    @PostMapping("/{userId}")
    @Operation(summary = "Create a bicycle")
    public ResponseEntity<BicycleResponseDto> createBicycle(@PathVariable(name = "userId") String userId,
            @RequestBody BicycleRequestDto bicycleRequestDto) {
        return ResponseEntity
                .ok(bicycleMapper.toResponseDto(
                        bicycleCommandService.handle(new CreateBicycleCommand(userId, bicycleRequestDto))));
    }

    @Transactional
    @PutMapping("/{bicycleId}")
    @Operation(summary = "Update a bicycle")
    public ResponseEntity<BicycleResponseDto> updateBicycle(@PathVariable(name = "bicycleId") Long bicycleId,
            @RequestBody BicycleRequestDto bicycleRequestDto) {
        return ResponseEntity.ok(bicycleMapper.toResponseDto(
                bicycleCommandService.handle(new UpdateBicycleCommand(bicycleId, bicycleRequestDto))));
    }

    @Transactional
    @DeleteMapping("/{bicycleId}")
    @Operation(summary = "Delete a bicycle")
    public ResponseEntity<String> deleteBicycle(@PathVariable(name = "bicycleId") Long bicycleId) {
        bicycleCommandService.handle(new DeleteBicycleCommand(bicycleId));
        return ResponseEntity.ok("Bicycle deleted successfully");
    }

    @Transactional
    @PutMapping("/temperature/{bicycleId}")
    @Operation(summary = "Update a bicycle temperature")
    public ResponseEntity<String> updateBicycleTemperature(@PathVariable(name = "bicycleId") Long bicycleId,
            @RequestBody TemperatureRequestDto temperatureRequestDto) {
        bicycleCommandService.handle(new PatchBicycleTemperatureCommand(temperatureRequestDto));
        return ResponseEntity.ok("Bicycle temperature updated successfully");
    }

    @Transactional
    @PutMapping("/velocity/{bicycleId}")
    @Operation(summary = "Update a bicycle velocity")
    public ResponseEntity<String> patchBicycleVelocity(@PathVariable(name = "bicycleId") Long bicycleId,
            @RequestBody VelocityRequestDto velocityRequestDto) {
        bicycleCommandService.handle(new PatchBicycleVelocityCommand(velocityRequestDto));
        return ResponseEntity.ok("Bicycle velocity updated successfully");
    }

    @Transactional
    @PutMapping("/gps/{bicycleId}")
    @Operation(summary = "Update a bicycle gps data")
    public ResponseEntity<String> patchBicycleGps(@PathVariable(name = "bicycleId") Long bicycleId,
            @RequestBody GpsRequestDto gpsRequestDto) {
        bicycleCommandService.handle(new PatchBicycleGpsCommand(gpsRequestDto));
        return ResponseEntity.ok("Bicycle gps updated successfully");
    }
}

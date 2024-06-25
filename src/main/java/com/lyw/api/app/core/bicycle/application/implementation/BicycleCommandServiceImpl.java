package com.lyw.api.app.core.bicycle.application.implementation;

import com.lyw.api.app.assets.application.services.VelocityCommandService;
import com.lyw.api.app.assets.domain.commands.CreateVelocityCommand;
import com.lyw.api.app.assets.domain.commands.PatchGpsCommand;
import com.lyw.api.app.assets.domain.commands.PatchVelocityCommand;
import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.core.bicycle.domain.commands.*;
import org.springframework.stereotype.Service;

import com.lyw.api.app.assets.application.services.GpsCommandService;
import com.lyw.api.app.assets.application.services.TemperatureCommandService;
import com.lyw.api.app.assets.domain.commands.CreateTemperatureCommand;
import com.lyw.api.app.assets.domain.commands.PatchTemperatureCommand;
import com.lyw.api.app.assets.domain.model.Gps;
import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.core.bicycle.application.services.BicycleCommandService;
import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.bicycle.domain.repositories.BicycleRepository;
import com.lyw.api.app.core.bicycle.infrastructure.dto.BicycleRequestDto;
import com.lyw.api.app.shared.utils.ValidationUtil;

@Service
public class BicycleCommandServiceImpl implements BicycleCommandService {

    private final BicycleRepository bicycleRepository;
    private final TemperatureCommandService temperatureService;
    private final VelocityCommandService velocityService;
    private final ValidationUtil validationUtil;
    private final GpsCommandService gpsService;

    public BicycleCommandServiceImpl(BicycleRepository bicycleRepository,
                                     TemperatureCommandService temperatureCommandService, VelocityCommandService velocityService, GpsCommandService gpsService, ValidationUtil validationUtil) {
        this.bicycleRepository = bicycleRepository;
        this.velocityService = velocityService;
        this.validationUtil = validationUtil;
        this.temperatureService = temperatureCommandService;
        this.gpsService = gpsService;
    }

    @Override
    public Bicycle handle(CreateBicycleCommand command) {
        Bicycle bicycle = new Bicycle();
        bicycle = setBicycle(bicycle, command.bicycleRequestDto());
        bicycle.setUser(validationUtil.findUserById(command.userId()));
        bicycle.setTemperature(temperatureService.handle(new CreateTemperatureCommand()));
        bicycle.setVelocity(velocityService.handle(new CreateVelocityCommand()));
        return bicycleRepository.save(bicycle);
    }

    @Override
    public Bicycle handle(UpdateBicycleCommand command) {
        Bicycle bicycle = validationUtil.findBicycleById(command.bicycleId());
        bicycle = setBicycle(bicycle, command.bicycleRequestDto());
        return bicycleRepository.save(bicycle);
    }

    @Override
    public void handle(DeleteBicycleCommand command) {
        Bicycle bicycle = validationUtil.findBicycleById(command.bicycleId());
        bicycleRepository.delete(bicycle);
    }

    @Override
    public Temperature handle(PatchBicycleTemperatureCommand command) {
        Bicycle bicycle = validationUtil.findBicycleById(command.temperatureRequestDto().getBicycleId());
        return temperatureService
                .handle(new PatchTemperatureCommand(command.temperatureRequestDto().getTemperature(),
                        bicycle.getTemperature().getId()));
    }

    @Override
    public Velocity handle(PatchBicycleVelocityCommand command) {
        Bicycle bicycle = validationUtil.findBicycleById(command.velocityRequestDto().getBicycleId());
        return velocityService
                .handle(new PatchVelocityCommand(command.velocityRequestDto().getVelocity(),
                        bicycle.getVelocity().getId()));
    }

    @Override
    public Gps handle(PatchBicycleGpsCommand command) {
        Bicycle bicycle = validationUtil.findBicycleById(command.gpsRequestDto().getBicycleId());
        return gpsService
                .handle(new PatchGpsCommand(command.gpsRequestDto().getLatitude(),
                        command.gpsRequestDto().getLongitude(), bicycle.getGps().getId()));
    }

    private Bicycle setBicycle(Bicycle bicycle, BicycleRequestDto bicycleRequestDto) {
        bicycle.setBicycleName(bicycleRequestDto.getBicycleName());
        bicycle.setBicycleDescription(bicycleRequestDto.getBicycleDescription());
        bicycle.setBicyclePrice(bicycleRequestDto.getBicyclePrice());
        bicycle.setBicycleSize(bicycleRequestDto.getBicycleSize());
        bicycle.setBicycleModel(bicycleRequestDto.getBicycleModel());
        bicycle.setImageData(bicycleRequestDto.getImageData());
        return bicycle;
    }


}

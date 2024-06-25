package com.lyw.api.app.assets.application.implementation;

import org.springframework.stereotype.Service;

import com.lyw.api.app.assets.application.services.TemperatureCommandService;
import com.lyw.api.app.assets.domain.commands.CreateTemperatureCommand;
import com.lyw.api.app.assets.domain.commands.PatchTemperatureCommand;
import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.assets.domain.repositories.TemperatureRepository;
import com.lyw.api.app.shared.utils.ValidationUtil;

@Service
public class TemperatureCommandServiceImpl implements TemperatureCommandService {

    private final TemperatureRepository temperatureRepository;
    private final ValidationUtil validationUtil;

    public TemperatureCommandServiceImpl(TemperatureRepository temperatureRepository, ValidationUtil validationUtil) {
        this.temperatureRepository = temperatureRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Temperature handle(CreateTemperatureCommand command) {
        Temperature temperature = new Temperature();
        temperature.setTemperature(25.0);
        return temperatureRepository.save(temperature);
    }

    @Override
    public Temperature handle(PatchTemperatureCommand command) {
        Temperature temperature = validationUtil.findTemperatureById(command.temperatureId());
        temperature.setTemperature(command.temperature());
        return temperatureRepository.save(temperature);
    }
}

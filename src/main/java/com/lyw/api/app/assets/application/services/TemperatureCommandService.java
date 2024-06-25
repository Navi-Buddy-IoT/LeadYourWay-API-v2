package com.lyw.api.app.assets.application.services;

import com.lyw.api.app.assets.domain.commands.CreateTemperatureCommand;
import com.lyw.api.app.assets.domain.commands.PatchTemperatureCommand;
import com.lyw.api.app.assets.domain.model.Temperature;

public interface TemperatureCommandService {
    Temperature handle(CreateTemperatureCommand command);

    Temperature handle(PatchTemperatureCommand command);
}

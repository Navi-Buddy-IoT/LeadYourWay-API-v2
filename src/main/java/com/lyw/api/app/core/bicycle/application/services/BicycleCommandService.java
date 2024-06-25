package com.lyw.api.app.core.bicycle.application.services;

import com.lyw.api.app.assets.domain.model.Gps;
import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.core.bicycle.domain.commands.*;
import com.lyw.api.app.core.bicycle.domain.model.Bicycle;

public interface BicycleCommandService {
    Bicycle handle(CreateBicycleCommand command);

    Bicycle handle(UpdateBicycleCommand command);

    Temperature handle(PatchBicycleTemperatureCommand command);

    Velocity handle(PatchBicycleVelocityCommand command);
    
    Gps handle(PatchBicycleGpsCommand comannd);

    void handle(DeleteBicycleCommand command);
}

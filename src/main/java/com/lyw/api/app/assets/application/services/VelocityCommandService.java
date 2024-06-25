package com.lyw.api.app.assets.application.services;

import com.lyw.api.app.assets.domain.commands.CreateVelocityCommand;
import com.lyw.api.app.assets.domain.commands.PatchVelocityCommand;
import com.lyw.api.app.assets.domain.model.Velocity;

public interface VelocityCommandService {
    Velocity handle(CreateVelocityCommand command);
    Velocity handle(PatchVelocityCommand command);
}

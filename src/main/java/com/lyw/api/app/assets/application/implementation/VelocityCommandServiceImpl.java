package com.lyw.api.app.assets.application.implementation;

import com.lyw.api.app.assets.application.services.VelocityCommandService;
import com.lyw.api.app.assets.domain.commands.CreateVelocityCommand;
import com.lyw.api.app.assets.domain.commands.PatchVelocityCommand;
import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.assets.domain.repositories.VelocityRepository;
import com.lyw.api.app.shared.utils.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class VelocityCommandServiceImpl implements VelocityCommandService {

    private final VelocityRepository velocityRepository;
    private final ValidationUtil validationUtil;

    public VelocityCommandServiceImpl(VelocityRepository velocityRepository, ValidationUtil validationUtil) {
        this.velocityRepository = velocityRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Velocity handle(CreateVelocityCommand command) {
        Velocity velocity = new Velocity();
        velocity.setVelocity(0.0);
        return velocityRepository.save(velocity);
    }

    @Override
    public Velocity handle(PatchVelocityCommand command) {
        Velocity velocity = validationUtil.findVelocityById(command.velocityId());
        velocity.setVelocity(command.velocity());
        return velocityRepository.save(velocity);
    }
}

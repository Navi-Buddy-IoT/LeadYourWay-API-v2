package com.lyw.api.app.assets.application.implementation;

import org.springframework.stereotype.Service;

import com.lyw.api.app.assets.application.services.GpsCommandService;
import com.lyw.api.app.assets.domain.commands.CreateGpsCommand;
import com.lyw.api.app.assets.domain.commands.PatchGpsCommand;
import com.lyw.api.app.assets.domain.model.Gps;
import com.lyw.api.app.assets.domain.repositories.GpsRepository;
import com.lyw.api.app.shared.utils.ValidationUtil;

@Service
public class GpsCommandServiceImpl implements GpsCommandService{

    private final GpsRepository gpsRepository;
    private final ValidationUtil validationUtil;

    public GpsCommandServiceImpl(GpsRepository gpsRepository, ValidationUtil validationUtil) {
        this.gpsRepository = gpsRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Gps handle(CreateGpsCommand command) {
        Gps gps = new Gps();
        gps.setLatitude(20.0);
        gps.setLongitude(20.0);
        return gpsRepository.save(gps);
    }

    @Override
    public Gps handle(PatchGpsCommand command) {
        Gps gps = validationUtil.findGpsById(command.gpsId());
        gps.setLatitude(command.latitude());
        gps.setLongitude(command.longitude());
        return gpsRepository.save(gps);
    }
    
}

package com.lyw.api.app.assets.application.services;

import com.lyw.api.app.assets.domain.commands.CreateGpsCommand;
import com.lyw.api.app.assets.domain.commands.PatchGpsCommand;
import com.lyw.api.app.assets.domain.model.Gps;

public interface GpsCommandService {
    Gps handle(CreateGpsCommand command);

    Gps handle(PatchGpsCommand command);
    
} 

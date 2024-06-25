package com.lyw.api.app.assets.application.services;

import com.lyw.api.app.assets.domain.quieries.GetVelocityByBicycleQuery;

public interface VelocityQueryService {
    double handle(GetVelocityByBicycleQuery query);
}

package com.lyw.api.app.assets.application.services;

import com.lyw.api.app.assets.domain.quieries.GetTemperatureByBicycleQuery;

public interface TemperatureQueryService {
    double handle(GetTemperatureByBicycleQuery query);
}

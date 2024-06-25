package com.lyw.api.app.assets.application.services;

import com.lyw.api.app.assets.domain.quieries.GetGpsByBicycleQuery;

public interface GpsQueryService {
    double handle(GetGpsByBicycleQuery query);
}

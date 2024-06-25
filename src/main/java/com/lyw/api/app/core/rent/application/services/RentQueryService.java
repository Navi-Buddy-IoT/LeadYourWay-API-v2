package com.lyw.api.app.core.rent.application.services;

import java.util.List;

import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.rent.domain.quieries.GetBicyclesByRentCardUserIdQuery;

public interface RentQueryService {
    List<Bicycle> handle(GetBicyclesByRentCardUserIdQuery query);
}

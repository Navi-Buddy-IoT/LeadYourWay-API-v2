package com.lyw.api.app.core.rent.application.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.rent.application.services.RentQueryService;
import com.lyw.api.app.core.rent.domain.quieries.GetBicyclesByRentCardUserIdQuery;
import com.lyw.api.app.core.rent.domain.repositories.RentRepository;

@Service
public class RentQueryServiceImpl implements RentQueryService {

    private final RentRepository rentRepository;

    public RentQueryServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public List<Bicycle> handle(GetBicyclesByRentCardUserIdQuery query) {
        return rentRepository.findRentsByCardUserId(query.userId()).stream()
                .map(rent -> rent.getBicycle())
                .toList();
    }

}

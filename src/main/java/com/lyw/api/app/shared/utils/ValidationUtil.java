package com.lyw.api.app.shared.utils;

import com.lyw.api.app.assets.domain.model.Velocity;
import com.lyw.api.app.assets.domain.repositories.VelocityRepository;
import org.springframework.stereotype.Component;

import com.lyw.api.app.assets.domain.model.Gps;
import com.lyw.api.app.assets.domain.model.Temperature;
import com.lyw.api.app.assets.domain.repositories.GpsRepository;
import com.lyw.api.app.assets.domain.repositories.TemperatureRepository;
import com.lyw.api.app.core.bicycle.domain.model.Availability;
import com.lyw.api.app.core.bicycle.domain.model.Bicycle;
import com.lyw.api.app.core.bicycle.domain.repositories.AvailabilityRepository;
import com.lyw.api.app.core.bicycle.domain.repositories.BicycleRepository;
import com.lyw.api.app.core.card.domain.model.Card;
import com.lyw.api.app.core.card.domain.repositories.CardRepository;
import com.lyw.api.app.core.rent.domain.model.Rent;
import com.lyw.api.app.core.rent.domain.repositories.RentRepository;
import com.lyw.api.app.iam.identity.domain.model.Role;
import com.lyw.api.app.iam.identity.domain.model.User;
import com.lyw.api.app.iam.identity.domain.repositories.RoleRepository;
import com.lyw.api.app.iam.identity.domain.repositories.UserRepository;
import com.lyw.api.app.shared.exceptions.ResourceNotFoundException;

import static com.lyw.api.app.shared.constants.CoreConstants.BIKER_ROLE_NAME;
import static com.lyw.api.app.shared.constants.CoreConstants.RENTER_ROLE_NAME;

@Component
public class ValidationUtil {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CardRepository cardRepository;
    private final BicycleRepository bicycleRepository;
    private final AvailabilityRepository availabilityRepository;
    private final RentRepository rentRepository;
    private final TemperatureRepository temperatureRepository;
    private final VelocityRepository velocityRepository;
    private final GpsRepository gpsRepository;

    public ValidationUtil(
            UserRepository userRepository,
            RoleRepository roleRepository,
            CardRepository cardRepository,
            BicycleRepository bicycleRepository,
            AvailabilityRepository availabilityRepository,
            RentRepository rentRepository,
            TemperatureRepository temperatureRepository, 
            VelocityRepository velocityRepository,
            GpsRepository gpsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cardRepository = cardRepository;
        this.bicycleRepository = bicycleRepository;
        this.availabilityRepository = availabilityRepository;
        this.rentRepository = rentRepository;
        this.temperatureRepository = temperatureRepository;
        this.velocityRepository = velocityRepository;
        this.gpsRepository = gpsRepository;
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public Role getBikerRole() {
        return roleRepository.findByName(BIKER_ROLE_NAME)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for biker"));
    }

    public Role getRenterRole() {
        return roleRepository.findByName(RENTER_ROLE_NAME)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for renter"));
    }

    public Card findCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));
    }

    public Bicycle findBicycleById(Long id) {
        return bicycleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bicycle not found with id: " + id));
    }

    public Availability findAvailabilityById(Long id) {
        return availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));
    }

    public Rent findRentById(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rent not found with id: " + id));
    }

    public Temperature findTemperatureById(Long id) {
        return temperatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Temperature not found with id: " + id));
    }

    public Velocity findVelocityById(Long id){
        return velocityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Velocity not found with id: " + id));
    }

    public Gps findGpsById(Long id){
        return gpsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Gps not found with id: " + id));
    }

}

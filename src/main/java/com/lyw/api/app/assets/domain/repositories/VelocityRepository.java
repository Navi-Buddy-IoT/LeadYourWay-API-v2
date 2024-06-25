package com.lyw.api.app.assets.domain.repositories;

import com.lyw.api.app.assets.domain.model.Velocity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VelocityRepository extends JpaRepository<Velocity, Long> {
}

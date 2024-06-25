package com.lyw.api.app.assets.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyw.api.app.assets.domain.model.Gps;

@Repository
public interface GpsRepository extends JpaRepository<Gps,Long>{

}

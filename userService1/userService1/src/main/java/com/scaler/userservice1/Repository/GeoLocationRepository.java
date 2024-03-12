package com.scaler.userservice1.Repository;

import com.scaler.userservice1.Models.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation,Long> {
    Optional<GeoLocation> findByLat(Double lat);
}

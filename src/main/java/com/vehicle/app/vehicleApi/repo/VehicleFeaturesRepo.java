package com.vehicle.app.vehicleApi.repo;

import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleFeaturesRepo extends JpaRepository<VehicleFeatures, Integer> {

}

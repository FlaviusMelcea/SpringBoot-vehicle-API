package com.vehicle.app.vehicleApi.repo;

import com.vehicle.app.vehicleApi.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
    void deleteByBrandAndYear(String brand, Integer year);
}

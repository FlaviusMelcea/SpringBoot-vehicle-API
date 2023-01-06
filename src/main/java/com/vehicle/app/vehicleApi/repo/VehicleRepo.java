package com.vehicle.app.vehicleApi.repo;

import com.vehicle.app.vehicleApi.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
    void deleteByBrandAndYear(String brand, Integer year);
    List<Vehicle> findVehicleByHasBuybackPromotionTrue();
}

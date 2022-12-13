package com.vehicle.app.vehicleApi.Repo;

import com.vehicle.app.vehicleApi.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

    @Transactional
    @Modifying
    void deleteByBrandAndYear(
            @Param("brand") String brand,
            @Param("year")  Integer year);
}

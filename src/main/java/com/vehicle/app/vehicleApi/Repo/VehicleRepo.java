package com.vehicle.app.vehicleApi.Repo;

import com.vehicle.app.vehicleApi.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

}

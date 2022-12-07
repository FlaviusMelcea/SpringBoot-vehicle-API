package com.vehicle.app.vehicleApi.Controller;

import com.vehicle.app.vehicleApi.Models.Vehicle;
import com.vehicle.app.vehicleApi.Repo.VehicleRepo;
import liquibase.pro.packaged.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    private VehicleRepo vehicleRepo;

    @GetMapping(value = "/vehicles")
    public List<Vehicle> getVehicles() {
        return vehicleRepo.findAll();
    }

    @PostMapping(value="/save")
    public String saveVehicle(@RequestBody Vehicle vehicle){
        vehicleRepo.save(vehicle);
        return "Vehicle saved!";
    }

    @PutMapping(value = "/update/{id}")
    public String updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle){
        Vehicle updatedVehicle = vehicleRepo.findById(id).get();
        updatedVehicle.setBrand(vehicle.getBrand());
        updatedVehicle.setModel(vehicle.getModel());
        updatedVehicle.setYear(vehicle.getYear());
        updatedVehicle.setPrice(vehicle.getPrice());
        updatedVehicle.setVin(vehicle.getVin());
        vehicleRepo.save(updatedVehicle);
        return "Vehicle updated!";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteVehicle(@PathVariable int id){
        Vehicle deleteVehicle = vehicleRepo.findById(id).get();
        vehicleRepo.delete(deleteVehicle);
        return "Delete vehicle with the id: " + id;
    }

}

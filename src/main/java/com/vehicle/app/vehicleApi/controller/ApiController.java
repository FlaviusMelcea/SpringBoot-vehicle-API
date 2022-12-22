package com.vehicle.app.vehicleApi.controller;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.vehicleBuyback.VehiclesBuyback;
import com.vehicle.app.vehicleApi.mapper.VehicleFeaturesMapper;
import com.vehicle.app.vehicleApi.mapper.VehicleMapper;
import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vehicles")
public class ApiController {
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;
    private final VehicleFeaturesMapper vehicleFeaturesMapper;

    @PostMapping
    public ResponseEntity<VehicleDto> create(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleDto);
        vehicleService.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDto> update(@PathVariable Integer id, @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleDto);
        vehicle.setId(id);

        vehicleService.save(vehicle);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicleDto);
    }

    @PutMapping("/{brand}/{model}/{year}")
    public ResponseEntity<VehicleFeaturesDto> update(@RequestBody VehicleFeaturesDto vehicleFeaturesDto) {
        VehicleFeatures vehicleFeatures = vehicleFeaturesMapper.toVehicleF(vehicleFeaturesDto);

        vehicleService.save(vehicleFeatures);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicleFeaturesDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        vehicleService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{brand}/{year}")
    public ResponseEntity<?> delete(@PathVariable String brand, @PathVariable Integer year) {
        vehicleService.deleteByBrandAndYear(brand, year);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> findAll() {
        List<VehicleDto> vehicleList = vehicleMapper.toVehicleDtos(vehicleService.findAll());
        return ResponseEntity.ok(vehicleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable Integer id) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        VehicleDto vehicleDto = vehicleMapper.toVehicleDto(vehicle.get());
        return ResponseEntity.ok(vehicleDto);
    }


    @GetMapping("/buyback")
    public List<VehiclesBuyback> findAllVehiclesWithBuyback() {
        return vehicleService.getAllActiveBuyback();
    }

}

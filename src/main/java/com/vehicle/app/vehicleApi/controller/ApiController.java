package com.vehicle.app.vehicleApi.controller;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.vehicleBuyback.VehiclesBuyback;
import com.vehicle.app.vehicleApi.mapper.VehicleFeaturesMapper;
import com.vehicle.app.vehicleApi.mapper.VehicleMapper;
import com.vehicle.app.vehicleApi.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vehicles")
public class ApiController {
    @Autowired
    private final VehicleService vehicleService;
    @PostMapping
    @ApiOperation(value = "Add a vehicle")
    public ResponseEntity<VehicleDto> create(@RequestBody VehicleDto vehicleDto) {
        vehicleService.save(VehicleMapper.INSTANCE.toVehicle(vehicleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a vehicle by id")
    public ResponseEntity<VehicleDto> update(@PathVariable Integer id, @RequestBody VehicleDto vehicleDto) {
        vehicleService.save(VehicleMapper.INSTANCE.toVehicle(vehicleDto), id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicleDto);
    }

    @PutMapping("/{brand}/{model}/{year}")
    @ApiOperation(value = "Update a vehicle by brand, model and year")
    public ResponseEntity<VehicleFeaturesDto> update(@RequestBody VehicleFeaturesDto vehicleFeaturesDto) {
        vehicleService.save(VehicleFeaturesMapper.INSTANCE.toVehicleF(vehicleFeaturesDto));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicleFeaturesDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete vehicle by id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        vehicleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{brand}/{year}")
    @ApiOperation(value = "Delete vehicle by brand and year")
    public ResponseEntity<?> delete(@PathVariable String brand, @PathVariable Integer year) {
        vehicleService.deleteByBrandAndYear(brand, year);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping
    @ApiOperation(value = "Find all vehicles")
    public List<VehicleDto> findAll() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find vehicles by id")
    public VehicleDto findVehicleById(@PathVariable Integer id) {
        return vehicleService.getById(id);
    }

    @GetMapping("/buyback")
    @ApiOperation(value = "Find vehicles with active buyback")
    public List<VehiclesBuyback> findAllVehiclesWithBuyback() {
        return vehicleService.getAllActiveBuyback();
    }

}

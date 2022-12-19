package com.vehicle.app.vehicleApi.Controller;

import com.vehicle.app.vehicleApi.Dto.VehicleDto;
import com.vehicle.app.vehicleApi.Dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.Dto.VehiclesDetailsDto;
import com.vehicle.app.vehicleApi.Models.Vehicle;
import com.vehicle.app.vehicleApi.Repo.VehicleRepo;
import com.vehicle.app.vehicleApi.Service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import liquibase.pro.packaged.V;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private VehicleService vehicleService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 404, message = "Not Found!")})
    @GetMapping("/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Get all Vehicles! ", response = VehicleDto.class, tags = "findAllVehicles")
    public List<VehicleDto> findAllVehicles() {
        return (List<VehicleDto>) vehicleService.getAllVehicles();
    }

    @PostMapping("/vehicles")
    public void saveVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleService.save(vehicleDto);
    }

    @GetMapping("/vehicles/{id}")
    public VehicleDto getVehicleById(@PathVariable Integer id) {
        return vehicleService.getById(id);
    }

    @GetMapping("/buybackVehicles")
    public List<VehiclesDetailsDto> findAllVehiclesWithBuyback() {
        return (List<VehiclesDetailsDto>) vehicleService.getAllActiveBuyback();
    }

    @DeleteMapping("/vehicles/{id}")
    void deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteById(id);
    }

    @DeleteMapping("/vehicles/{brand}/{year}")
    void deleteVehicleByBrandAndYear(@PathVariable String brand, @PathVariable Integer year) {
        vehicleService.deleteByBrandAndYear(brand, year);
    }

    @PostMapping("/vehicles/update/{id}")
    void updateVehiclesById(@PathVariable Integer id, @RequestBody VehicleDto vehicleDto) {
        vehicleService.save(vehicleDto, id);
    }

    @PostMapping("/vehicles/updateVehiclesFeatures/{model}/{brand}/{year}")
    void updateVehiclesFeaturesByModelBrandAndYear(@RequestBody List<VehicleFeaturesDto> vehicleFeaturesDtos,
                                              @PathVariable String model,
                                              @PathVariable String brand, @PathVariable Integer year) {
        vehicleService.updateVehicleFeatures(model,brand,year,vehicleFeaturesDtos);
    }
}

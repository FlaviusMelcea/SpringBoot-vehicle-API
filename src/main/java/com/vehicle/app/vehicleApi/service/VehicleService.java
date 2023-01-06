package com.vehicle.app.vehicleApi.service;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.mapper.VehicleMapper;
import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.repo.VehicleFeaturesRepo;
import com.vehicle.app.vehicleApi.repo.VehicleRepo;
import com.vehicle.app.vehicleApi.vehicleBuyback.VehiclesBuyback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
    private VehicleRepo vehicleRepo;
    private VehicleFeaturesRepo vehicleFeaturesRepo;
    public VehicleService(VehicleRepo vehicleRepo, VehicleFeaturesRepo vehicleFeaturesRepo) {
        this.vehicleRepo = vehicleRepo;
        this.vehicleFeaturesRepo = vehicleFeaturesRepo;
    }

    public VehicleDto getById(Integer id) {
        Vehicle vehicle = vehicleRepo.getById(id);

        return VehicleMapper.INSTANCE.toVehicleDto(vehicle);
    }

    public Vehicle save(Vehicle vehicle) {
        setUnitsMade(vehicle);
        setEmissionLevel(vehicle);
        return vehicleRepo.save(vehicle);
    }

    public void save(Vehicle vehicle, Integer id) {
        vehicle.setId(id);
        save(vehicle);
    }

    public List<VehicleDto> getVehicles(){
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        List<Vehicle> vehicleList = vehicleRepo.findAll();
        vehicleList.forEach(vehicle-> vehicleDtoList.add(VehicleMapper.INSTANCE.toVehicleDto(vehicle)));
        return vehicleDtoList;
    }
    public VehicleFeatures save(VehicleFeatures vehicleFeatures) {
        return vehicleFeaturesRepo.save(vehicleFeatures);
    }

    public void deleteById(Integer id) {
        vehicleRepo.deleteById(id);
    }

    public void deleteByBrandAndYear(String brand, Integer year) {
        vehicleRepo.deleteByBrandAndYear(brand, year);
    }

    public List<VehiclesBuyback> getAllActiveBuyback() {
        List<Vehicle> vehicleList = vehicleRepo.findVehicleByHasBuybackPromotionTrue();
        List<VehiclesBuyback> vehicleBuyBack = new ArrayList<>();
        vehicleList.forEach(vehicle -> {
            VehicleDto vehicleDto = VehicleMapper.INSTANCE.toVehicleDto(vehicle);
            vehicleBuyBack.add(new VehiclesBuyback(
                    vehicleDto.getBrand(),
                    vehicleDto.getModel(),
                    vehicleDto.getYear()));
        });
        return vehicleBuyBack;
    }

    private void setUnitsMade(Vehicle vehicle) {
        if (vehicle.getUnitsMade() == null) {
            if (vehicle.getPrice() <= 10000) {
                vehicle.setUnitsMade(600);
            } else if (vehicle.getPrice() < 30000) {
                vehicle.setUnitsMade(450);
            } else if (vehicle.getPrice() > 30000) {
                vehicle.setUnitsMade(300);
            }
        }
    }

    private void setEmissionLevel(Vehicle vehicle) {
        if (vehicle.getReleaseDate().isEqual(LocalDate.parse("2010-01-01")) || vehicle.getReleaseDate().isBefore(LocalDate.parse("2010-01-01"))) {
            vehicle.setEmissionLevel("EURO4");
        }
        if (vehicle.getReleaseDate().isAfter(LocalDate.parse("2010-01-01")) && vehicle.getReleaseDate().isBefore(LocalDate.parse("2016-03-04"))) {
            vehicle.setEmissionLevel("EURO5");
        }
        if (vehicle.getReleaseDate().isEqual(LocalDate.parse("2016-03-04")) || vehicle.getReleaseDate().isAfter(LocalDate.parse("2016-03-04"))) {
            vehicle.setEmissionLevel("EURO6");
        }
    }

}

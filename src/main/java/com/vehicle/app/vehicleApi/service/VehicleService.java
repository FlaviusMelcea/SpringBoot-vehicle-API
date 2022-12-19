package com.vehicle.app.vehicleApi.service;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.mapper.VehicleMapper;
import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.repo.VehicleFeaturesRepo;
import com.vehicle.app.vehicleApi.repo.VehicleRepo;
import com.vehicle.app.vehicleApi.vehicleBuyback.VehiclesBuyback;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
      @Autowired
      private VehicleRepo vehicleRepo;
      @Autowired
      private VehicleFeaturesRepo vehicleFeaturesRepo;

      public List<Vehicle> findAll() {
            return vehicleRepo.findAll();
      }
      public Optional<Vehicle> findById(Integer id) {
            return vehicleRepo.findById(id);
      }

      public Vehicle save(Vehicle vehicle) {
            setUnitsMade(vehicle);
            setEmissionLevel(vehicle);
            return vehicleRepo.save(vehicle);
      }

    public VehicleFeatures save(VehicleFeatures vehicleFeatures) {
        return vehicleFeaturesRepo.save(vehicleFeatures);
    }

      public void deleteById(Integer id) {
            vehicleRepo.deleteById(id);
      }

      public void deleteByBrandAndYear(String brand, Integer year){
          vehicleRepo.deleteByBrandAndYear(brand,year);
     }

      public List<VehiclesBuyback> getAllActiveBuyback() {
        List<Vehicle> vehicleList = vehicleRepo.findAll();
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        List<VehiclesBuyback> vehicleBuyBack = new ArrayList<>();
        for(Vehicle vehicle : vehicleList){
            if(vehicle.getHasBuybackPromotion()){
                vehicleDtoList.add(VehicleMapper.INSTANCE.toVehicleDto(vehicle));
            }
        }
          for(VehicleDto vehicleDto : vehicleDtoList){
              vehicleBuyBack.add(new VehiclesBuyback(
                      vehicleDto.getBrand(),
                      vehicleDto.getModel(),
                      vehicleDto.getYear()));
          }
          return vehicleBuyBack;
    }

        private void setUnitsMade(Vehicle vehicle){
          if(vehicle.getUnitsMade() == null){
              if(vehicle.getPrice()<=10000){
                  vehicle.setUnitsMade(600);
              }
              else if(vehicle.getPrice()<30000){
                  vehicle.setUnitsMade(450);
              }
              else if(vehicle.getPrice()>30000){
                  vehicle.setUnitsMade(300);
              }
          }
        }

        private void setEmissionLevel(Vehicle vehicle){
            if(vehicle.getReleaseDate().isEqual(LocalDate.parse("2010-01-01"))|| vehicle.getReleaseDate().isBefore(LocalDate.parse("2010-01-01"))){
                vehicle.setEmissionLevel("EURO4");
            }
            if(vehicle.getReleaseDate().isAfter(LocalDate.parse("2010-01-01"))&& vehicle.getReleaseDate().isBefore(LocalDate.parse("2016-03-04"))){
                vehicle.setEmissionLevel("EURO5");
            }
            if(vehicle.getReleaseDate().isEqual(LocalDate.parse("2016-03-04"))|| vehicle.getReleaseDate().isAfter(LocalDate.parse("2016-03-04"))){
                vehicle.setEmissionLevel("EURO6");
            }
        }

}

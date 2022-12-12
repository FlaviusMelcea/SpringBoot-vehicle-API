//package com.vehicle.app.vehicleApi.Service;
//
//import com.vehicle.app.vehicleApi.Dto.VehicleDto;
//import com.vehicle.app.vehicleApi.Dto.VehicleFeaturesDto;
//import com.vehicle.app.vehicleApi.Dto.VehiclesDetailsDto;
//import com.vehicle.app.vehicleApi.Mapper.VehicleMapper;
//import com.vehicle.app.vehicleApi.Models.Vehicle;
//import com.vehicle.app.vehicleApi.Repo.VehicleFeaturesRepo;
//import com.vehicle.app.vehicleApi.Repo.VehicleRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class VehicleService {
//    @Autowired
//    private VehicleRepo vehicleRepo;
//    @Autowired
//    private VehicleFeaturesRepo vehicleFeaturesRepo;
//
//    public List<VehiclesDetailsDto> getAllActiveBuyback() {
//        List<VehicleDto> carDtoList = new ArrayList<>();
//        List<Vehicle> carList = VehicleRepo.findAll();
//
//        carList.forEach((car -> {
//            if (vehicle.getEmissionLevel() == null)
//                setEmissionLevel(vehicle);
//            if (car.getUnitsMade() == null)
//                setUnitsMade(vehicle);
//            if (car.getHasBuybackPromotion())
//                vehicleDtoList.add(VehicleMapper.INSTANCE.carToCarDto(car));
//        }));
//
//        List<VehiclesDetailsDto> carDetails = new ArrayList<>();
//        carDtoList.forEach(carDto -> {
//            vehicleDetails.add(new VehiclesDetailsDto(vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getYear()));
//        });
//
//        return vehicleDetails;
//    }
//
//    public void save(VehicleDto VehicleDto) {
//        Vehicle vehicle = VehicleMapper.INSTANCE.convertVehicleDtoToVehicle(VehicleDto);
//        if (vehicle.getEmissionLevel() == null)
//            setEmissionLevel(vehicle);
//        if (vehicle.getUnitsMade() == null)
//            setUnitsMade(vehicle);
//        vehicleRepo.save(vehicle);
//    }
//
//    public void save(VehicleDto vehicleDto, Integer id) {
//        Vehicle vehicleUpdate = VehicleMapper.INSTANCE.convertVehicleDtoToVehicle(vehicleDto);
//        vehicleUpdate.setId(id);
//        if (vehicleUpdate.getEmissionLevel() == null)
//            setEmissionLevel(vehicleUpdate);
//        if (vehicleUpdate.getUnitsMade() == null)
//            setUnitsMade(vehicleUpdate);
//        vehicleRepo.save(vehicleUpdate);
//    }
//
//    public List<VehicleDto> getAllCars() {
//        List<VehicleDto> carDtoList = new ArrayList<>();
//        List<Vehicle> carList = VehicleRepo.findAll();
//        carList.forEach((car -> {
//            vehicleDtoList.add(VehicleMapper.INSTANCE.convertVehicleToVehicleDto(vehicle));
//        }));
//
//        return vehicleDtoList;
//    }
//
//
//    public VehicleDto getById(Integer id) {
//        Vehicle vehicle = vehicleRepo.getById(id);
//
//        return VehicleMapper.INSTANCE.convertVehicleToVehicleDto(vehicle);
//    }
//
//    public void deleteById(Integer id) {
//        vehicleRepo.deleteById(id);
//    }
//
//    public void setEmissionLevel(Vehicle vehicle) {
//
//        Date releaseDate = vehicle.getReleaseDate();
//
//        if (releaseDate.isBefore(EmissionLevel.EURO4.localDate) || releaseDate.equals(EmissionLevel.EURO4.localDate))
//        {
//            vehicle.setEmissionLevel("EURO4");
//        }
//
//        else if (releaseDate.isAfter(EmissionLevel.EURO4.localDate) && releaseDate.isBefore(EmissionLevel.EURO5.localDate))
//        {
//            vehicle.setEmissionLevel("EURO5");
//        }
//
//        else if (releaseDate.isAfter(EmissionLevel.EURO6.localDate) || releaseDate.equals(EmissionLevel.EURO6.localDate))
//        {
//            vehicle.setEmissionLevel("EURO6");
//            vehicleRepo.save(vehicle);
//        }
//
//    }
//
//    public void setUnitsMade(Vehicle vehicle) {
//
//        double vehiclePrice = vehicle.getPrice();
//
//        if (vehiclePrice <= 10000){
//            vehicle.setUnitsMade(600);
//        }
//        else if (vehiclePrice < 30000)
//        {
//            vehicle.setUnitsMade(450);
//        }
//        else
//        {
//            vehicle.setUnitsMade(300);
//        }
//        vehicleRepo.save(vehicle);
//    }
//
//    public void deleteByBrandAndYear(String brand, Integer year) {
//        vehicleRepo.deleteByBrandAndYear(brand, year);
//    }
//
//    public void updateCarFeatures(String model, String brand, Integer year, List<CarFeatureDto> carFeatureDto) {
//        List<Vehicle> carList = vehicleRepo.findAll();
//        List<VehicleDto> carDtoList = new ArrayList<>();
//        vehicleList.forEach(vehicle ->
//        {
//            if (vehicle.getModel().equals(model) && vehicle.getBrand().equals(brand) && vehicle.getYear().equals(year))
//                vehicleDtoList.add(VehicleMapper.INSTANCE.convertVehicleToVehicleDto(vehicle));
//        });
//        vehicleDtoList.forEach(vehicleDto -> {
//            List<VehicleFeaturesDto> vehicleFeatureDtoList = vehicleDto.getVehicleFeatureDtoList();
//            vehicleFeatureDtoList.addAll(vehicleFeatureDto);
//            vehicleDto.setVehicleFeatureDtoList(vehicleFeatureDtoList);
//            vehicleRepo.save(VehicleMapper.INSTANCE.convertVehicleDtoToVehicle(vehicleDto));
//        });
//    }
//
//}

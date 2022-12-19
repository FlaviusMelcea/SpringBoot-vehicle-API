package com.vehicle.app.vehicleApi.mapper;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.models.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
//tst

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);
    @Mapping(target="vehicleFeaturesDtoList",source = "vehicleFeaturesList")
    VehicleDto toVehicleDto(Vehicle vehicle);
    List<VehicleDto> toVehicleDtos(List<Vehicle> vehicles);
    @Mapping(target="vehicleFeaturesList",source = "vehicleFeaturesDtoList")
      Vehicle toVehicle(VehicleDto vehicleDto);
}

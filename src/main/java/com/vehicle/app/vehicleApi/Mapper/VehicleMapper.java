package com.vehicle.app.vehicleApi.Mapper;

import com.vehicle.app.vehicleApi.Dto.VehicleDto;
import com.vehicle.app.vehicleApi.Models.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target="vehicleFeaturesDtoList",source = "vehicleFeaturesList")
    VehicleDto convertVehicleToVehicleDto(Vehicle vehicle);
    @Mapping(target="vehicleFeaturesList",source = "vehicleFeaturesDtoList")
    Vehicle convertVehicleDtoToVehicle(VehicleDto vehicleDto);
}

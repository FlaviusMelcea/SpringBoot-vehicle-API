package com.vehicle.app.vehicleApi.mapper;

import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleFeaturesMapper {

    VehicleFeaturesMapper INSTANCE = Mappers.getMapper(VehicleFeaturesMapper.class);
    VehicleFeaturesDto toVehicleFDto(VehicleFeatures vehicleFeatures);
    VehicleFeatures toVehicleF(VehicleFeaturesDto vehicleFeaturesDto);
}

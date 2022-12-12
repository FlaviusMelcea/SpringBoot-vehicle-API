package com.vehicle.app.vehicleApi.Mapper;

import com.vehicle.app.vehicleApi.Dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.Models.VehicleFeatures;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface VehicleFeaturesMapper {
        VehicleFeaturesMapper INSTANCE = Mappers.getMapper(VehicleFeaturesMapper.class);
        VehicleFeaturesDto convertVFeatureToVFeaturesDto(VehicleFeatures vehicleFeatures);
        VehicleFeatures convertVFeatureDtoToVFeature(VehicleFeaturesDto vehicleFeaturesDto);
}

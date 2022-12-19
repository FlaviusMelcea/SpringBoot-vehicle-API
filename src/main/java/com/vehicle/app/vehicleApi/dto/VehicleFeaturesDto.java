package com.vehicle.app.vehicleApi.Dto;

import io.micrometer.core.lang.Nullable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleFeaturesDto {
    @Nullable
    private Integer id;
    private String code;
    private String name;
    private String description;
    private double price;
}

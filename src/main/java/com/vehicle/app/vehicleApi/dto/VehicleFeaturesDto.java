package com.vehicle.app.vehicleApi.dto;

import io.micrometer.core.lang.Nullable;
import lombok.*;
import org.springframework.lang.NonNull;

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
    private Double price;
}

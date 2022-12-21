package com.vehicle.app.vehicleApi.dto;


import lombok.*;
import org.jetbrains.annotations.Nullable;
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

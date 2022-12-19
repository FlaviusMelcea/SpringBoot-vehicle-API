package com.vehicle.app.vehicleApi.Dto;

import io.micrometer.core.lang.Nullable;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleDto {
    @Nullable
    private Integer id;
    private String vin;
    private String brand;
    private String model;
    private Integer year;
    private Double price;

    private List<VehicleFeaturesDto> vehicleFeaturesDtoList;

    private String color;
    private LocalDate releaseDate;
    private Integer yearsOfWarranty;
    private Boolean hasBuybackPromotion;

    @Nullable
    private String emissionLevel;
    @Nullable
    private Integer unitsMade;
    @Nullable
    private Integer userRating;

}

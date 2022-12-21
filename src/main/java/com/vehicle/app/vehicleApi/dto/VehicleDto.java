package com.vehicle.app.vehicleApi.dto;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleDto {
    @Nullable
    @Id
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
    private Integer unitsMade;
    @Nullable
    private Integer userRating;
    @Nullable
    private String emissionLevel;

}

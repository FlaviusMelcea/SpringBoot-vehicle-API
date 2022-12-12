package com.vehicle.app.vehicleApi.Dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehiclesDetailsDto {

    private String vin;

    private String brand;

    private String model;

    private int year;

    private String color;

}

package com.vehicle.app.vehicleApi.vehicleBuyback;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VehiclesBuybackDto {

    private String brand;

    private String model;

    private Integer year;


}

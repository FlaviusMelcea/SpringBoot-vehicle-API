package com.vehicle.app.vehicleApi.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "vehicle_features")

public class VehicleFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Code;
    private String name;
    private String description;
    private double price;

}

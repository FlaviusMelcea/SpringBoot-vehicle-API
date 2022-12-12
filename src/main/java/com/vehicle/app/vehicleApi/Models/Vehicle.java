package com.vehicle.app.vehicleApi.Models;

import io.micrometer.core.lang.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NonNull
    private String vin;

    @Column
    @NonNull
    private String brand;

    @Column
    @NonNull
    private String model;

    @Column
    @NonNull
    private int year;

    @Column
    @NonNull
    private double price;

    @NonNull
    @Column(name="Vehicle features")
    @OneToMany(targetEntity = VehicleFeatures.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle id", referencedColumnName = "id")
    private List<VehicleFeatures> vehicleFeaturesList;

    @NonNull
    @Column
    private String color;

    @NonNull
    @Column
    private Date releaseDate;

    @NonNull
    @Column
    private int yearsOfWarranty;

    @NonNull
    @Column
    private boolean hasBuybackPromotion;

    @Nullable
    @Column
    private int unitsMade;

    @Nullable
    @Column
    private int userRating;

    @Nullable
    @Column
    private String emissionLevel;


}

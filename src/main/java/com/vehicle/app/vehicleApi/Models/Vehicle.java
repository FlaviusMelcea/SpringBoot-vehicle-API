package com.vehicle.app.vehicleApi.Models;

import io.micrometer.core.lang.Nullable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "vin")
    @NonNull
    private String vin;

    @Column(name = "brand")
    @NonNull
    private String brand;

    @Column(name = "model")
    @NonNull
    private String model;

    @Column(name = "year")
    @NonNull
    private Integer year;

    @Column(name="price")
    @NonNull
    private double price;

    @NonNull
    @Column(name="features")
    @OneToMany(targetEntity = VehicleFeatures.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleId", referencedColumnName = "id")
    private List<VehicleFeatures> vehicleFeaturesList;

    @NonNull
    @Column(name="color")
    private String color;

    @NonNull
    @Column(name="releasedate")
    private LocalDate releaseDate;

    @NonNull
    @Column(name="yearsofwarranty")
    private Integer yearsOfWarranty;

    @NonNull
    @Column(name="hasbuybackpromotion")
    private Boolean hasBuybackPromotion;

    @Nullable
    @Column(name="unitsmade")
    private Integer unitsMade;

    @Nullable
    @Column(name="userrating")
    private Integer userRating;

    @Nullable
    @Column(name="emissionlevel")
    private String emissionLevel;



}

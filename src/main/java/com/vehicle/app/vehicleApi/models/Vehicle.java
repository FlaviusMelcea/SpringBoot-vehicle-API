package com.vehicle.app.vehicleApi.models;

import io.micrometer.core.lang.Nullable;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Entity
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NonNull
    @Column(name = "Vin")
    private String vin;

    @NonNull
    @Column(name = "Brand")
    private String brand;

    @NonNull
    @Column(name = "Model")
    private String model;

    @NonNull
    @Column(name = "Year")
    private Integer year;

    @NonNull
    @Column(name="Price")
    private Double price;

    @NonNull
    @Column(name="Features")
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

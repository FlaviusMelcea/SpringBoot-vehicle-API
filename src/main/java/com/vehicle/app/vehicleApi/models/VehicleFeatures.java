package com.vehicle.app.vehicleApi.Models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Vehicle features")
public class VehicleFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Code;
    private String name;
    private String description;
    private double price;

    public boolean equals(Object x){

        if(this==x)
            return true;

        if(x==null || Hibernate.getClass(this)!=Hibernate.getClass(x))
            return false;

        VehicleFeatures that = (VehicleFeatures)  x;
        return id!=null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}

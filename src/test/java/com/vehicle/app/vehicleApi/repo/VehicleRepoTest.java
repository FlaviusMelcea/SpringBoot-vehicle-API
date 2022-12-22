package com.vehicle.app.vehicleApi.repo;

import static org.junit.Assert.assertEquals;

import com.vehicle.app.vehicleApi.models.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {VehicleRepo.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.vehicle.app.vehicleApi.models"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
public class VehicleRepoTest {
    @Autowired
    private VehicleRepo vehicleRepo;

    @Test
    @Ignore
    public void testDeleteByBrandAndYear() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle.setUnitsMade(1);
        vehicle.setUserRating(1);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setBrand("Brand");
        vehicle1.setColor("Color");
        vehicle1.setEmissionLevel("Emission Level");
        vehicle1.setHasBuybackPromotion(true);
        vehicle1.setModel("Model");
        vehicle1.setPrice(10.0d);
        vehicle1.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle1.setUnitsMade(1);
        vehicle1.setUserRating(1);
        vehicle1.setVehicleFeaturesList(new ArrayList<>());
        vehicle1.setVin("Vin");
        vehicle1.setYear(1);
        vehicle1.setYearsOfWarranty(1);
        vehicleRepo.save(vehicle);
        vehicleRepo.save(vehicle1);
        vehicleRepo.deleteByBrandAndYear("foo", 1);
        assertEquals(2, vehicleRepo.findAll().size());
    }
}


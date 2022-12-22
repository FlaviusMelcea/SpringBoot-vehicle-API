package com.vehicle.app.vehicleApi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.repo.VehicleFeaturesRepo;
import com.vehicle.app.vehicleApi.repo.VehicleRepo;
import com.vehicle.app.vehicleApi.vehicleBuyback.VehiclesBuyback;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(classes = {VehicleService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleServiceTest {
    @MockBean
    private VehicleFeaturesRepo vehicleFeaturesRepo;

    @MockBean
    private VehicleRepo vehicleRepo;

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void testFindAll() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleRepo.findAll()).thenReturn(vehicleList);
        List<Vehicle> actualFindAllResult = vehicleService.findAll();
        assertSame(vehicleList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(vehicleRepo).findAll();
    }

    @Test
    public void testFindById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle.setUnitsMade(1);
        vehicle.setUserRating(1);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepo.findById((Integer) any())).thenReturn(ofResult);
        Optional<Vehicle> actualFindByIdResult = vehicleService.findById(1);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(vehicleRepo).findById((Integer) any());
    }

    @Test
    public void testSave() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle.setUnitsMade(1);
        vehicle.setUserRating(1);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);
        when(vehicleRepo.save((Vehicle) any())).thenReturn(vehicle);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setBrand("Brand");
        vehicle1.setColor("Color");
        vehicle1.setEmissionLevel("Emission Level");
        vehicle1.setHasBuybackPromotion(true);
        vehicle1.setId(1);
        vehicle1.setModel("Model");
        vehicle1.setPrice(10.0d);
        vehicle1.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle1.setUnitsMade(1);
        vehicle1.setUserRating(1);
        vehicle1.setVehicleFeaturesList(new ArrayList<>());
        vehicle1.setVin("Vin");
        vehicle1.setYear(1);
        vehicle1.setYearsOfWarranty(1);
        assertSame(vehicle, vehicleService.save(vehicle1));
        verify(vehicleRepo).save((Vehicle) any());
        assertEquals("EURO4", vehicle1.getEmissionLevel());
    }

    @Test
    public void testSave2() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle.setUnitsMade(1);
        vehicle.setUserRating(1);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);
        when(vehicleRepo.save((Vehicle) any())).thenReturn(vehicle);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setBrand("Brand");
        vehicle1.setColor("Color");
        vehicle1.setEmissionLevel("Emission Level");
        vehicle1.setHasBuybackPromotion(true);
        vehicle1.setId(1);
        vehicle1.setModel("Model");
        vehicle1.setPrice(10.0d);
        vehicle1.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle1.setUnitsMade(null);
        vehicle1.setUserRating(1);
        vehicle1.setVehicleFeaturesList(new ArrayList<>());
        vehicle1.setVin("Vin");
        vehicle1.setYear(1);
        vehicle1.setYearsOfWarranty(1);
        assertSame(vehicle, vehicleService.save(vehicle1));
        verify(vehicleRepo).save((Vehicle) any());
        assertEquals(600, vehicle1.getUnitsMade().intValue());
        assertEquals("EURO4", vehicle1.getEmissionLevel());
    }

    @Test
    public void testSave3() {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("Lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);
        when(vehicleFeaturesRepo.save((VehicleFeatures) any())).thenReturn(vehicleFeatures);

        VehicleFeatures vehicleFeatures1 = new VehicleFeatures();
        vehicleFeatures1.setCode("Code");
        vehicleFeatures1.setDescription("Lol");
        vehicleFeatures1.setId(1);
        vehicleFeatures1.setName("Name");
        vehicleFeatures1.setPrice(10.0d);
        assertSame(vehicleFeatures, vehicleService.save(vehicleFeatures1));
        verify(vehicleFeaturesRepo).save((VehicleFeatures) any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(vehicleRepo).deleteById((Integer) any());
        vehicleService.deleteById(1);
        verify(vehicleRepo).deleteById((Integer) any());
        assertTrue(vehicleService.findAll().isEmpty());
    }

    @Test
    public void testDeleteByBrandAndYear() {
        doNothing().when(vehicleRepo).deleteByBrandAndYear((String) any(), (Integer) any());
        vehicleService.deleteByBrandAndYear("Brand", 1);
        verify(vehicleRepo).deleteByBrandAndYear((String) any(), (Integer) any());
        assertTrue(vehicleService.findAll().isEmpty());
    }

    @Test
    public void testGetAllActiveBuyback() {
        when(vehicleRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(vehicleService.getAllActiveBuyback().isEmpty());
        verify(vehicleRepo).findAll();
    }

    @Test
    public void testGetAllActiveBuyback2() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle.setUnitsMade(1);
        vehicle.setUserRating(1);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        when(vehicleRepo.findAll()).thenReturn(vehicleList);
        List<VehiclesBuyback> actualAllActiveBuyback = vehicleService.getAllActiveBuyback();
        assertEquals(1, actualAllActiveBuyback.size());
        VehiclesBuyback getResult = actualAllActiveBuyback.get(0);
        assertEquals("Brand", getResult.getBrand());
        assertEquals(1, getResult.getYear().intValue());
        assertEquals("Model", getResult.getModel());
        verify(vehicleRepo).findAll();
    }

    @Test
    public void testGetAllActiveBuyback3() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
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
        vehicle1.setId(1);
        vehicle1.setModel("Model");
        vehicle1.setPrice(10.0d);
        vehicle1.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle1.setUnitsMade(1);
        vehicle1.setUserRating(1);
        vehicle1.setVehicleFeaturesList(new ArrayList<>());
        vehicle1.setVin("Vin");
        vehicle1.setYear(1);
        vehicle1.setYearsOfWarranty(1);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle);
        when(vehicleRepo.findAll()).thenReturn(vehicleList);
        List<VehiclesBuyback> actualAllActiveBuyback = vehicleService.getAllActiveBuyback();
        assertEquals(2, actualAllActiveBuyback.size());
        VehiclesBuyback getResult = actualAllActiveBuyback.get(0);
        assertEquals(1, getResult.getYear().intValue());
        VehiclesBuyback getResult1 = actualAllActiveBuyback.get(1);
        assertEquals(1, getResult1.getYear().intValue());
        assertEquals("Model", getResult1.getModel());
        assertEquals("Brand", getResult1.getBrand());
        assertEquals("Model", getResult.getModel());
        assertEquals("Brand", getResult.getBrand());
        verify(vehicleRepo).findAll();
    }

    @Test
    public void testGetAllActiveBuyback4() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(false);
        vehicle.setId(1);
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
        vehicle1.setId(1);
        vehicle1.setModel("Model");
        vehicle1.setPrice(10.0d);
        vehicle1.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicle1.setUnitsMade(1);
        vehicle1.setUserRating(1);
        vehicle1.setVehicleFeaturesList(new ArrayList<>());
        vehicle1.setVin("Vin");
        vehicle1.setYear(1);
        vehicle1.setYearsOfWarranty(1);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle);
        when(vehicleRepo.findAll()).thenReturn(vehicleList);
        List<VehiclesBuyback> actualAllActiveBuyback = vehicleService.getAllActiveBuyback();
        assertEquals(1, actualAllActiveBuyback.size());
        VehiclesBuyback getResult = actualAllActiveBuyback.get(0);
        assertEquals("Brand", getResult.getBrand());
        assertEquals(1, getResult.getYear().intValue());
        assertEquals("Model", getResult.getModel());
        verify(vehicleRepo).findAll();
    }
}


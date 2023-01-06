package com.vehicle.app.vehicleApi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.repo.VehicleFeaturesRepo;
import com.vehicle.app.vehicleApi.repo.VehicleRepo;
import com.vehicle.app.vehicleApi.vehicleBuyback.VehiclesBuyback;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
    @Mock
    private VehicleFeaturesRepo vehicleFeaturesRepo;
    @Mock
    private VehicleRepo vehicleRepo;
    @InjectMocks VehicleService vehicleService;

    @Test
    public void testGetById() {
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
        ArrayList<VehicleFeatures> vehicleFeaturesList = new ArrayList<>();
        vehicle.setVehicleFeaturesList(vehicleFeaturesList);
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);
        VehicleRepo vehicleRepo = mock(VehicleRepo.class);
        when(vehicleRepo.getById((Integer) any())).thenReturn(vehicle);
        VehicleDto actualById = (new VehicleService(vehicleRepo, mock(VehicleFeaturesRepo.class))).getById(1);
        assertEquals("Brand", actualById.getBrand());
        assertEquals(1, actualById.getYearsOfWarranty().intValue());
        assertEquals(1, actualById.getYear().intValue());
        assertEquals("Vin", actualById.getVin());
        assertEquals(vehicleFeaturesList, actualById.getVehicleFeaturesDtoList());
        assertEquals(1, actualById.getUserRating().intValue());
        assertEquals(1, actualById.getUnitsMade().intValue());
        assertEquals("1970-01-02", actualById.getReleaseDate().toString());
        assertEquals(10.0d, actualById.getPrice().doubleValue(), 0.0);
        assertEquals("Model", actualById.getModel());
        assertEquals(1, actualById.getId().intValue());
        assertTrue(actualById.getHasBuybackPromotion());
        assertEquals("Emission Level", actualById.getEmissionLevel());
        assertEquals("Color", actualById.getColor());
        verify(vehicleRepo).getById((Integer) any());
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
        VehicleRepo vehicleRepo = mock(VehicleRepo.class);
        when(vehicleRepo.save((Vehicle) any())).thenReturn(vehicle);
        VehicleService vehicleService = new VehicleService(vehicleRepo, mock(VehicleFeaturesRepo.class));

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
    public void testGetVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(4L));
        vehicle.setUnitsMade(4);
        vehicle.setUserRating(4);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(4);
        vehicle.setYearsOfWarranty(4);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        VehicleRepo vehicleRepo = mock(VehicleRepo.class);
        when(vehicleRepo.findAll()).thenReturn(vehicleList);
        assertEquals(1, (new VehicleService(vehicleRepo, mock(VehicleFeaturesRepo.class))).getVehicles().size());
        verify(vehicleRepo).findAll();
    }

    @Test
    public void testDeleteById() {
        VehicleRepo vehicleRepo = mock(VehicleRepo.class);
        doNothing().when(vehicleRepo).deleteById((Integer) any());
        (new VehicleService(vehicleRepo, mock(VehicleFeaturesRepo.class))).deleteById(1);
        verify(vehicleRepo).deleteById((Integer) any());
    }

    @Test
    public void testDeleteByBrandAndYear() {
        VehicleRepo vehicleRepo = mock(VehicleRepo.class);
        doNothing().when(vehicleRepo).deleteByBrandAndYear((String) any(), (Integer) any());
        (new VehicleService(vehicleRepo, mock(VehicleFeaturesRepo.class))).deleteByBrandAndYear("Brand", 1);
        verify(vehicleRepo).deleteByBrandAndYear((String) any(), (Integer) any());
    }

    @Test
    public void testGetAllActiveBuyback() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setColor("Color");
        vehicle.setEmissionLevel("Emission Level");
        vehicle.setHasBuybackPromotion(true);
        vehicle.setId(1);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setReleaseDate(LocalDate.ofEpochDay(4L));
        vehicle.setUnitsMade(4);
        vehicle.setUserRating(4);
        vehicle.setVehicleFeaturesList(new ArrayList<>());
        vehicle.setVin("Vin");
        vehicle.setYear(4);
        vehicle.setYearsOfWarranty(4);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        VehicleRepo vehicleRepo = mock(VehicleRepo.class);
        when(vehicleRepo.findVehicleByHasBuybackPromotionTrue()).thenReturn(vehicleList);
        List<VehiclesBuyback> actualAllActiveBuyback = (new VehicleService(vehicleRepo, mock(VehicleFeaturesRepo.class)))
                .getAllActiveBuyback();
        assertEquals(1, actualAllActiveBuyback.size());
        VehiclesBuyback getResult = actualAllActiveBuyback.get(0);
        assertEquals("Brand", getResult.getBrand());
        assertEquals(4, getResult.getYear().intValue());
        assertEquals("Model", getResult.getModel());
        verify(vehicleRepo).findVehicleByHasBuybackPromotionTrue();
    }
    @Test
    public void testGetaAllActiveBuyback2() {
        when(vehicleRepo.findVehicleByHasBuybackPromotionTrue()).thenReturn(new ArrayList<>());
        assertTrue(vehicleService.getAllActiveBuyback().isEmpty());
        verify(vehicleRepo).findVehicleByHasBuybackPromotionTrue();
    }
}

package com.vehicle.app.vehicleApi.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {VehicleMapperImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleMapperImplTest {
    @Autowired
    private VehicleMapperImpl vehicleMapperImpl;

    @Test
    public void testToVehicleDto() {
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
        VehicleDto actualToVehicleDtoResult = vehicleMapperImpl.toVehicleDto(vehicle);
        assertEquals("Brand", actualToVehicleDtoResult.getBrand());
        assertEquals(1, actualToVehicleDtoResult.getYearsOfWarranty().intValue());
        assertEquals(1, actualToVehicleDtoResult.getYear().intValue());
        assertEquals("Vin", actualToVehicleDtoResult.getVin());
        assertEquals(vehicleFeaturesList, actualToVehicleDtoResult.getVehicleFeaturesDtoList());
        assertEquals(1, actualToVehicleDtoResult.getUserRating().intValue());
        assertEquals(1, actualToVehicleDtoResult.getUnitsMade().intValue());
        assertEquals("1970-01-02", actualToVehicleDtoResult.getReleaseDate().toString());
        assertEquals(10.0d, actualToVehicleDtoResult.getPrice().doubleValue(), 0.0);
        assertEquals("Model", actualToVehicleDtoResult.getModel());
        assertEquals(1, actualToVehicleDtoResult.getId().intValue());
        assertTrue(actualToVehicleDtoResult.getHasBuybackPromotion());
        assertEquals("Emission Level", actualToVehicleDtoResult.getEmissionLevel());
        assertEquals("Color", actualToVehicleDtoResult.getColor());
    }

    @Test
    public void testToVehicleDto2() {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("Lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);

        ArrayList<VehicleFeatures> vehicleFeaturesList = new ArrayList<>();
        vehicleFeaturesList.add(vehicleFeatures);

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
        vehicle.setVehicleFeaturesList(vehicleFeaturesList);
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);
        VehicleDto actualToVehicleDtoResult = vehicleMapperImpl.toVehicleDto(vehicle);
        assertEquals("Brand", actualToVehicleDtoResult.getBrand());
        assertEquals(1, actualToVehicleDtoResult.getYearsOfWarranty().intValue());
        assertEquals(1, actualToVehicleDtoResult.getYear().intValue());
        assertEquals("Vin", actualToVehicleDtoResult.getVin());
        List<VehicleFeaturesDto> vehicleFeaturesDtoList = actualToVehicleDtoResult.getVehicleFeaturesDtoList();
        assertEquals(1, vehicleFeaturesDtoList.size());
        assertEquals(1, actualToVehicleDtoResult.getUserRating().intValue());
        assertEquals("Color", actualToVehicleDtoResult.getColor());
        assertEquals(1, actualToVehicleDtoResult.getUnitsMade().intValue());
        assertTrue(actualToVehicleDtoResult.getHasBuybackPromotion());
        assertEquals(10.0d, actualToVehicleDtoResult.getPrice().doubleValue(), 0.0);
        assertEquals("Model", actualToVehicleDtoResult.getModel());
        assertEquals(1, actualToVehicleDtoResult.getId().intValue());
        assertEquals("1970-01-02", actualToVehicleDtoResult.getReleaseDate().toString());
        assertEquals("Emission Level", actualToVehicleDtoResult.getEmissionLevel());
        VehicleFeaturesDto getResult = vehicleFeaturesDtoList.get(0);
        assertEquals("Code", getResult.getCode());
        assertEquals(10.0d, getResult.getPrice().doubleValue(), 0.0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getId().intValue());
        assertEquals("Lol", getResult.getDescription());
    }

    @Test
    public void testToVehicleDtos() {
        assertTrue(vehicleMapperImpl.toVehicleDtos(new ArrayList<>()).isEmpty());
    }

    @Test
    public void testToVehicleDtos2() {
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
        assertEquals(1, vehicleMapperImpl.toVehicleDtos(vehicleList).size());
    }

    @Test
    public void testToVehicleDtos3() {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("Lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);

        ArrayList<VehicleFeatures> vehicleFeaturesList = new ArrayList<>();
        vehicleFeaturesList.add(vehicleFeatures);

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
        vehicle.setVehicleFeaturesList(vehicleFeaturesList);
        vehicle.setVin("Vin");
        vehicle.setYear(1);
        vehicle.setYearsOfWarranty(1);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        assertEquals(1, vehicleMapperImpl.toVehicleDtos(vehicleList).size());
    }


    @Test
    public void testToVehicle2() {
        assertNull(vehicleMapperImpl.toVehicle(null));
    }

    @Test
    public void testToVehicle3() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        Vehicle actualToVehicleResult = vehicleMapperImpl.toVehicle(new VehicleDto(1,
                "vehicleFeaturesList is marked non-null but is null", "vehicleFeaturesList is marked non-null but is null",
                "vehicleFeaturesList is marked non-null but is null", 1, 10.0d, vehicleFeaturesDtoList,
                "vehicleFeaturesList is marked non-null but is null", LocalDate.ofEpochDay(1L), 1, true, 1, 1,
                "vehicleFeaturesList is marked non-null but is null"));
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getBrand());
        assertEquals(1, actualToVehicleResult.getYearsOfWarranty().intValue());
        assertEquals(1, actualToVehicleResult.getYear().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getVin());
        assertEquals(vehicleFeaturesDtoList, actualToVehicleResult.getVehicleFeaturesList());
        assertEquals(1, actualToVehicleResult.getUserRating().intValue());
        assertEquals(1, actualToVehicleResult.getUnitsMade().intValue());
        assertEquals("1970-01-02", actualToVehicleResult.getReleaseDate().toString());
        assertEquals(10.0d, actualToVehicleResult.getPrice().doubleValue(), 0.0);
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getModel());
        assertEquals(1, actualToVehicleResult.getId().intValue());
        assertTrue(actualToVehicleResult.getHasBuybackPromotion());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getEmissionLevel());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getColor());
    }

    @Test
    public void testToVehicle9() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        vehicleFeaturesDtoList.add(new VehicleFeaturesDto());
        Vehicle actualToVehicleResult = vehicleMapperImpl.toVehicle(new VehicleDto(1,
                "vehicleFeaturesList is marked non-null but is null", "vehicleFeaturesList is marked non-null but is null",
                "vehicleFeaturesList is marked non-null but is null", 1, 10.0d, vehicleFeaturesDtoList,
                "vehicleFeaturesList is marked non-null but is null", LocalDate.ofEpochDay(1L), 1, true, 1, 1,
                "vehicleFeaturesList is marked non-null but is null"));
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getBrand());
        assertEquals(1, actualToVehicleResult.getYearsOfWarranty().intValue());
        assertEquals(1, actualToVehicleResult.getYear().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getVin());
        List<VehicleFeatures> vehicleFeaturesList = actualToVehicleResult.getVehicleFeaturesList();
        assertEquals(1, vehicleFeaturesList.size());
        assertEquals(1, actualToVehicleResult.getUserRating().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getColor());
        assertEquals(1, actualToVehicleResult.getUnitsMade().intValue());
        assertTrue(actualToVehicleResult.getHasBuybackPromotion());
        assertEquals(10.0d, actualToVehicleResult.getPrice().doubleValue(), 0.0);
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getModel());
        assertEquals(1, actualToVehicleResult.getId().intValue());
        assertEquals("1970-01-02", actualToVehicleResult.getReleaseDate().toString());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getEmissionLevel());
        VehicleFeatures getResult = vehicleFeaturesList.get(0);
        assertNull(getResult.getCode());
        assertNull(getResult.getName());
        assertNull(getResult.getId());
        assertNull(getResult.getDescription());
    }
    @Test
    public void testToVehicle14() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        vehicleFeaturesDtoList.add(null);
        Vehicle actualToVehicleResult = vehicleMapperImpl.toVehicle(new VehicleDto(1,
                "vehicleFeaturesList is marked non-null but is null", "vehicleFeaturesList is marked non-null but is null",
                "vehicleFeaturesList is marked non-null but is null", 1, 10.0d, vehicleFeaturesDtoList,
                "vehicleFeaturesList is marked non-null but is null", LocalDate.ofEpochDay(1L), 1, true, 1, 1,
                "vehicleFeaturesList is marked non-null but is null"));
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getBrand());
        assertEquals(1, actualToVehicleResult.getYearsOfWarranty().intValue());
        assertEquals(1, actualToVehicleResult.getYear().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getVin());
        List<VehicleFeatures> vehicleFeaturesList = actualToVehicleResult.getVehicleFeaturesList();
        assertEquals(vehicleFeaturesDtoList, vehicleFeaturesList);
        assertEquals(1, vehicleFeaturesList.size());
        assertEquals(1, actualToVehicleResult.getUserRating().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getColor());
        assertEquals(1, actualToVehicleResult.getUnitsMade().intValue());
        assertTrue(actualToVehicleResult.getHasBuybackPromotion());
        assertEquals(10.0d, actualToVehicleResult.getPrice().doubleValue(), 0.0);
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getModel());
        assertEquals(1, actualToVehicleResult.getId().intValue());
        assertEquals("1970-01-02", actualToVehicleResult.getReleaseDate().toString());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getEmissionLevel());
    }

    @Test
    public void testToVehicle15() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        vehicleFeaturesDtoList
                .add(new VehicleFeaturesDto(1, "Code", "Name", "Lol", 10.0d));
        Vehicle actualToVehicleResult = vehicleMapperImpl.toVehicle(new VehicleDto(1,
                "vehicleFeaturesList is marked non-null but is null", "vehicleFeaturesList is marked non-null but is null",
                "vehicleFeaturesList is marked non-null but is null", 1, 10.0d, vehicleFeaturesDtoList,
                "vehicleFeaturesList is marked non-null but is null", LocalDate.ofEpochDay(1L), 1, true, 1, 1,
                "vehicleFeaturesList is marked non-null but is null"));
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getBrand());
        assertEquals(1, actualToVehicleResult.getYearsOfWarranty().intValue());
        assertEquals(1, actualToVehicleResult.getYear().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getVin());
        List<VehicleFeatures> vehicleFeaturesList = actualToVehicleResult.getVehicleFeaturesList();
        assertEquals(1, vehicleFeaturesList.size());
        assertEquals(1, actualToVehicleResult.getUserRating().intValue());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getColor());
        assertEquals(1, actualToVehicleResult.getUnitsMade().intValue());
        assertTrue(actualToVehicleResult.getHasBuybackPromotion());
        assertEquals(10.0d, actualToVehicleResult.getPrice().doubleValue(), 0.0);
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getModel());
        assertEquals(1, actualToVehicleResult.getId().intValue());
        assertEquals("1970-01-02", actualToVehicleResult.getReleaseDate().toString());
        assertEquals("vehicleFeaturesList is marked non-null but is null", actualToVehicleResult.getEmissionLevel());
        VehicleFeatures getResult = vehicleFeaturesList.get(0);
        assertEquals("Code", getResult.getCode());
        assertEquals(10.0d, getResult.getPrice(), 0.0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getId().intValue());
        assertEquals("Lol", getResult.getDescription());
    }

    @Test
    public void testVehicleFeaturesToVehicleFeaturesDto() {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("Lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);
        VehicleFeaturesDto actualVehicleFeaturesToVehicleFeaturesDtoResult = vehicleMapperImpl
                .vehicleFeaturesToVehicleFeaturesDto(vehicleFeatures);
        assertEquals("Code", actualVehicleFeaturesToVehicleFeaturesDtoResult.getCode());
        assertEquals(10.0d, actualVehicleFeaturesToVehicleFeaturesDtoResult.getPrice().doubleValue(), 0.0);
        assertEquals("Name", actualVehicleFeaturesToVehicleFeaturesDtoResult.getName());
        assertEquals(1, actualVehicleFeaturesToVehicleFeaturesDtoResult.getId().intValue());
        assertEquals("Lol",
                actualVehicleFeaturesToVehicleFeaturesDtoResult.getDescription());
    }

    @Test
    public void testVehicleFeaturesListToVehicleFeaturesDtoList() {
        assertTrue(vehicleMapperImpl.vehicleFeaturesListToVehicleFeaturesDtoList(new ArrayList<>()).isEmpty());
    }

    @Test
    public void testVehicleFeaturesListToVehicleFeaturesDtoList2() {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("Lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);

        ArrayList<VehicleFeatures> vehicleFeaturesList = new ArrayList<>();
        vehicleFeaturesList.add(vehicleFeatures);
        List<VehicleFeaturesDto> actualVehicleFeaturesListToVehicleFeaturesDtoListResult = vehicleMapperImpl
                .vehicleFeaturesListToVehicleFeaturesDtoList(vehicleFeaturesList);
        assertEquals(1, actualVehicleFeaturesListToVehicleFeaturesDtoListResult.size());
        VehicleFeaturesDto getResult = actualVehicleFeaturesListToVehicleFeaturesDtoListResult.get(0);
        assertEquals("Code", getResult.getCode());
        assertEquals(10.0d, getResult.getPrice().doubleValue(), 0.0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getId().intValue());
        assertEquals("Lol", getResult.getDescription());
    }

    @Test
    public void testVehicleFeaturesDtoToVehicleFeatures() {
        VehicleFeatures actualVehicleFeaturesDtoToVehicleFeaturesResult = vehicleMapperImpl
                .vehicleFeaturesDtoToVehicleFeatures(new VehicleFeaturesDto());
        assertNull(actualVehicleFeaturesDtoToVehicleFeaturesResult.getCode());
        assertNull(actualVehicleFeaturesDtoToVehicleFeaturesResult.getName());
        assertNull(actualVehicleFeaturesDtoToVehicleFeaturesResult.getId());
        assertNull(actualVehicleFeaturesDtoToVehicleFeaturesResult.getDescription());
    }

    @Test
    public void testVehicleFeaturesDtoToVehicleFeatures2() {
        assertNull(vehicleMapperImpl.vehicleFeaturesDtoToVehicleFeatures(null));
    }

    @Test
    public void testVehicleFeaturesDtoToVehicleFeatures3() {
        VehicleFeatures actualVehicleFeaturesDtoToVehicleFeaturesResult = vehicleMapperImpl
                .vehicleFeaturesDtoToVehicleFeatures(
                        new VehicleFeaturesDto(1, "Code", "Name", "Lol", 10.0d));
        assertEquals("Code", actualVehicleFeaturesDtoToVehicleFeaturesResult.getCode());
        assertEquals(10.0d, actualVehicleFeaturesDtoToVehicleFeaturesResult.getPrice(), 0.0);
        assertEquals("Name", actualVehicleFeaturesDtoToVehicleFeaturesResult.getName());
        assertEquals(1, actualVehicleFeaturesDtoToVehicleFeaturesResult.getId().intValue());
        assertEquals("Lol",
                actualVehicleFeaturesDtoToVehicleFeaturesResult.getDescription());
    }

    @Test
    public void testVehicleFeaturesDtoListToVehicleFeaturesList() {
        assertTrue(vehicleMapperImpl.vehicleFeaturesDtoListToVehicleFeaturesList(new ArrayList<>()).isEmpty());
    }

    @Test
    public void testVehicleFeaturesDtoListToVehicleFeaturesList2() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        vehicleFeaturesDtoList.add(new VehicleFeaturesDto());
        List<VehicleFeatures> actualVehicleFeaturesDtoListToVehicleFeaturesListResult = vehicleMapperImpl
                .vehicleFeaturesDtoListToVehicleFeaturesList(vehicleFeaturesDtoList);
        assertEquals(1, actualVehicleFeaturesDtoListToVehicleFeaturesListResult.size());
        VehicleFeatures getResult = actualVehicleFeaturesDtoListToVehicleFeaturesListResult.get(0);
        assertNull(getResult.getCode());
        assertNull(getResult.getName());
        assertNull(getResult.getId());
        assertNull(getResult.getDescription());
    }

    @Test
    public void testVehicleFeaturesDtoListToVehicleFeaturesList3() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        vehicleFeaturesDtoList.add(null);
        assertEquals(1, vehicleMapperImpl.vehicleFeaturesDtoListToVehicleFeaturesList(vehicleFeaturesDtoList).size());
    }

    @Test
    public void testVehicleFeaturesDtoListToVehicleFeaturesList4() {
        ArrayList<VehicleFeaturesDto> vehicleFeaturesDtoList = new ArrayList<>();
        vehicleFeaturesDtoList
                .add(new VehicleFeaturesDto(1, "Code", "Name", "Lol", 10.0d));
        List<VehicleFeatures> actualVehicleFeaturesDtoListToVehicleFeaturesListResult = vehicleMapperImpl
                .vehicleFeaturesDtoListToVehicleFeaturesList(vehicleFeaturesDtoList);
        assertEquals(1, actualVehicleFeaturesDtoListToVehicleFeaturesListResult.size());
        VehicleFeatures getResult = actualVehicleFeaturesDtoListToVehicleFeaturesListResult.get(0);
        assertEquals("Code", getResult.getCode());
        assertEquals(10.0d, getResult.getPrice(), 0.0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getId().intValue());
        assertEquals("Lol", getResult.getDescription());
    }
}


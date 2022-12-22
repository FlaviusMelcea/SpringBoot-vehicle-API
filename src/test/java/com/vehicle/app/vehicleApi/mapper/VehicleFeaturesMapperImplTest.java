package com.vehicle.app.vehicleApi.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {VehicleFeaturesMapperImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleFeaturesMapperImplTest {
    @Autowired
    private VehicleFeaturesMapperImpl vehicleFeaturesMapperImpl;

    @Test
    public void testToVehicleFDto() {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("Lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);
        VehicleFeaturesDto actualToVehicleFDtoResult = vehicleFeaturesMapperImpl.toVehicleFDto(vehicleFeatures);
        assertEquals("Code", actualToVehicleFDtoResult.getCode());
        assertEquals(10.0d, actualToVehicleFDtoResult.getPrice().doubleValue(), 0.0);
        assertEquals("Name", actualToVehicleFDtoResult.getName());
        assertEquals(1, actualToVehicleFDtoResult.getId().intValue());
        assertEquals("Lol", actualToVehicleFDtoResult.getDescription());
    }

    @Test
    public void testToVehicleF() {
        VehicleFeatures actualToVehicleFResult = vehicleFeaturesMapperImpl.toVehicleF(new VehicleFeaturesDto());
        assertNull(actualToVehicleFResult.getCode());
        assertNull(actualToVehicleFResult.getName());
        assertNull(actualToVehicleFResult.getId());
        assertNull(actualToVehicleFResult.getDescription());
    }

    @Test
    public void testToVehicleF2() {
        assertNull(vehicleFeaturesMapperImpl.toVehicleF(null));
    }

    @Test
    public void testToVehicleF3() {
        VehicleFeatures actualToVehicleFResult = vehicleFeaturesMapperImpl
                .toVehicleF(new VehicleFeaturesDto(1, "Code", "Name", "Lol", 10.0d));
        assertEquals("Code", actualToVehicleFResult.getCode());
        assertEquals(10.0d, actualToVehicleFResult.getPrice(), 0.0);
        assertEquals("Name", actualToVehicleFResult.getName());
        assertEquals(1, actualToVehicleFResult.getId().intValue());
        assertEquals("Lol", actualToVehicleFResult.getDescription());
    }
}


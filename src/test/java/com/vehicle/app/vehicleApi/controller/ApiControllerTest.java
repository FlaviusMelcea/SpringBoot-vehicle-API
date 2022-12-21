package com.vehicle.app.vehicleApi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.mapper.VehicleFeaturesMapper;
import com.vehicle.app.vehicleApi.mapper.VehicleMapper;
import com.vehicle.app.vehicleApi.models.Vehicle;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.service.VehicleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ApiController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApiControllerTest {
    @Autowired
    private ApiController apiController;

    @MockBean
    private VehicleFeaturesMapper vehicleFeaturesMapper;

    @MockBean
    private VehicleMapper vehicleMapper;

    @MockBean
    private VehicleService vehicleService;

    @Test
    @Ignore
    public void testCreate() throws Exception {
        //TODO.. finish this

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON);

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setBrand("Brand");
        vehicleDto.setColor("Color");
        vehicleDto.setEmissionLevel("Emission Level");
        vehicleDto.setHasBuybackPromotion(true);
        vehicleDto.setId(1);
        vehicleDto.setModel("Model");
        vehicleDto.setPrice(10.0d);
        vehicleDto.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicleDto.setUnitsMade(1);
        vehicleDto.setUserRating(1);
        vehicleDto.setVin("Vin");
        vehicleDto.setYear(1);
        vehicleDto.setYearsOfWarranty(1);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(vehicleDto));
        MockMvcBuilders.standaloneSetup(apiController).build().perform(requestBuilder);
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(vehicleService).deleteById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/vehicles/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDelete2() throws Exception {
        doNothing().when(vehicleService).deleteById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/vehicles/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(apiController).build().perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDelete3() throws Exception {
        doNothing().when(vehicleService).deleteByBrandAndYear((String) any(), (Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/vehicles/{brand}/{year}", "Brand",
                1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDelete4() throws Exception {
        doNothing().when(vehicleService).deleteByBrandAndYear((String) any(), (Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/vehicles/{brand}/{year}", "Brand",
                1);
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(apiController).build().perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testFindAll() throws Exception {
        when(vehicleService.findAll()).thenReturn(new ArrayList<>());
        when(vehicleMapper.toVehicleDtos((List<Vehicle>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles");
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testFindAll2() throws Exception {
        when(vehicleService.findAll()).thenReturn(new ArrayList<>());
        when(vehicleMapper.toVehicleDtos((List<Vehicle>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/vehicles");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testFindById() throws Exception {
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
        when(vehicleService.findById((Integer) any())).thenReturn(ofResult);
        when(vehicleMapper.toVehicleDto((Vehicle) any())).thenReturn(new VehicleDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles/{id}", 1);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(String.join("",
                "{\"id\":null,\"vin\":null,\"brand\":null,\"model\":null,\"year\":null,\"price\":null,\"vehicleFeaturesDtoList\":null"
                        + ",\"color\":null,\"",
                System.getProperty("jdk.debug"),
                "Date\":null,\"yearsOfWarranty\":null,\"hasBuybackPromotion\":null,\"unitsMade\":null,\"userRating\":null,"
                        + "\"emissionLevel\":null}")));
    }

    @Test
    public void testCreate2() throws Exception {
        when(vehicleService.findAll()).thenReturn(new ArrayList<>());
        when(vehicleMapper.toVehicleDtos((List<Vehicle>) any())).thenReturn(new ArrayList<>());

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setBrand("Brand");
        vehicleDto.setColor("Color");
        vehicleDto.setEmissionLevel("Emission Level");
        vehicleDto.setHasBuybackPromotion(true);
        vehicleDto.setId(1);
        vehicleDto.setModel("Model");
        vehicleDto.setPrice(10.0d);
        vehicleDto.setReleaseDate(null);
        vehicleDto.setUnitsMade(1);
        vehicleDto.setUserRating(1);
        vehicleDto.setVin("Vin");
        vehicleDto.setYear(1);
        vehicleDto.setYearsOfWarranty(1);
        String content = (new ObjectMapper()).writeValueAsString(vehicleDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testFindAllVehiclesWithBuyback() throws Exception {
        when(vehicleService.getAllActiveBuyback()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles/buyback");
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testFindAllVehiclesWithBuyback2() throws Exception {
        when(vehicleService.getAllActiveBuyback()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/vehicles/buyback");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testUpdate() throws Exception {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("The characteristics of someone or something");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);
        when(vehicleService.save((VehicleFeatures) any())).thenReturn(vehicleFeatures);

        VehicleFeatures vehicleFeatures1 = new VehicleFeatures();
        vehicleFeatures1.setCode("Code");
        vehicleFeatures1.setDescription("The characteristics of someone or something");
        vehicleFeatures1.setId(1);
        vehicleFeatures1.setName("Name");
        vehicleFeatures1.setPrice(10.0d);
        when(vehicleFeaturesMapper.toVehicleF((VehicleFeaturesDto) any())).thenReturn(vehicleFeatures1);

        VehicleFeaturesDto vehicleFeaturesDto = new VehicleFeaturesDto();
        vehicleFeaturesDto.setCode("Code");
        vehicleFeaturesDto.setDescription("The characteristics of someone or something");
        vehicleFeaturesDto.setId(1);
        vehicleFeaturesDto.setName("Name");
        vehicleFeaturesDto.setPrice(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(vehicleFeaturesDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/vehicles/{brand}/*/{year}", "Brand", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"code\":\"Code\",\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"price"
                                        + "\":10.0}"));
    }

    @Test
    @Ignore
    public void testUpdate2() throws Exception {
     //TODO.. finish this

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/vehicles/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setBrand("Brand");
        vehicleDto.setColor("Color");
        vehicleDto.setEmissionLevel("Emission Level");
        vehicleDto.setHasBuybackPromotion(true);
        vehicleDto.setId(1);
        vehicleDto.setModel("Model");
        vehicleDto.setPrice(10.0d);
        vehicleDto.setReleaseDate(LocalDate.ofEpochDay(1L));
        vehicleDto.setUnitsMade(1);
        vehicleDto.setUserRating(1);
        vehicleDto.setVin("Vin");
        vehicleDto.setYear(1);
        vehicleDto.setYearsOfWarranty(1);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(vehicleDto));
        MockMvcBuilders.standaloneSetup(apiController).build().perform(requestBuilder);
    }

    @Test
    public void testUpdate3() throws Exception {
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
        when(vehicleService.save((Vehicle) any())).thenReturn(vehicle);

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
        when(vehicleMapper.toVehicle((VehicleDto) any())).thenReturn(vehicle1);

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setBrand("Brand");
        vehicleDto.setColor("Color");
        vehicleDto.setEmissionLevel("Emission Level");
        vehicleDto.setHasBuybackPromotion(true);
        vehicleDto.setId(1);
        vehicleDto.setModel("Model");
        vehicleDto.setPrice(10.0d);
        vehicleDto.setReleaseDate(null);
        vehicleDto.setUnitsMade(1);
        vehicleDto.setUserRating(1);
        vehicleDto.setVin("Vin");
        vehicleDto.setYear(1);
        vehicleDto.setYearsOfWarranty(1);
        String content = (new ObjectMapper()).writeValueAsString(vehicleDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/vehicles/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(String.join("",
                "{\"id\":1,\"vin\":\"Vin\",\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"price\":10.0,\"vehicleFeaturesDtoList\""
                        + ":null,\"color\":\"Color\",\"",
                System.getProperty("jdk.debug"),
                "Date\":null,\"yearsOfWarranty\":1,\"hasBuybackPromotion\":true,\"unitsMade\":1,\"userRating\":1,\"emissionLevel"
                        + "\":\"Emission Level\"}")));
    }
}


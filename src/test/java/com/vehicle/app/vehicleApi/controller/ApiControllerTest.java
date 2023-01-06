package com.vehicle.app.vehicleApi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.app.vehicleApi.dto.VehicleDto;
import com.vehicle.app.vehicleApi.dto.VehicleFeaturesDto;
import com.vehicle.app.vehicleApi.models.VehicleFeatures;
import com.vehicle.app.vehicleApi.service.VehicleService;

import java.time.LocalDate;
import java.util.ArrayList;

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
    private VehicleService vehicleService;
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
    public void testCreate() throws Exception {
        when(vehicleService.getVehicles()).thenReturn(new ArrayList<>());

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
    public void testFindAll() throws Exception {
        when(vehicleService.getVehicles()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles");
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
    public void testFindVehicleById() throws Exception {
        when(vehicleService.getById((Integer) any())).thenReturn(new VehicleDto());
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
    public void testUpdate() throws Exception {
        VehicleFeatures vehicleFeatures = new VehicleFeatures();
        vehicleFeatures.setCode("Code");
        vehicleFeatures.setDescription("lol");
        vehicleFeatures.setId(1);
        vehicleFeatures.setName("Name");
        vehicleFeatures.setPrice(10.0d);
        when(vehicleService.save((VehicleFeatures) any())).thenReturn(vehicleFeatures);

        VehicleFeaturesDto vehicleFeaturesDto = new VehicleFeaturesDto();
        vehicleFeaturesDto.setCode("Code");
        vehicleFeaturesDto.setDescription("lol");
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
                                "{\"id\":1,\"code\":\"Code\",\"name\":\"Name\",\"description\":\"lol\",\"price"
                                        + "\":10.0}"));
    }
}

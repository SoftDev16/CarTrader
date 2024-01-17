package com.cars.trader.unit;


import com.cars.trader.car.CarController;
import com.cars.trader.car.CarResponseDTO;
import com.cars.trader.car.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;


    private CarResponseDTO sampleCarResponseDTO;

    @BeforeEach
    public void setup() {
        sampleCarResponseDTO = createSampleCarResponseDTO();
    }

    private CarResponseDTO createSampleCarResponseDTO() {
        return new CarResponseDTO(
                "Ford",
                "Focus",
                2020,
                "Blue",
                new BigDecimal(26000),
                "https://cartrader.s3.eu-west-1.amazonaws.com/focus.png"
        );
    }

    @Test
    public void testGetAllCars() throws Exception {
        when(carService.getAllCars()).thenReturn(Arrays.asList(sampleCarResponseDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make").value("Ford"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value("Focus"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].colour").value("Blue"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("26000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].imageUrl").value("https://cartrader.s3.eu-west-1.amazonaws.com/focus.png"));
    }

    @Test
    public void testGetAllCarsWhenEmpty() throws Exception {
        when(carService.getAllCars()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    public void testGetAllCarsServiceException() throws Exception {
        when(carService.getAllCars()).thenThrow(new RuntimeException("Database connection failure"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string(containsString("An unexpected error occurred")));
    }

}
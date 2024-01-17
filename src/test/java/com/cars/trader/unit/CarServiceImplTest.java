package com.cars.trader.unit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cars.trader.car.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;
    private CarDTO carDTO;

    @BeforeEach
    void setUp() {
        car = new Car(1L, "Ford", "Focus", 2020, "Blue", new BigDecimal(26000), "VIN12345678901234567", "https://image.url");
        carDTO = new CarDTO("Ford", "Focus", 2020, "Blue", new BigDecimal(26000), "VIN12345678901234567", "https://image.url");
    }

    @Test
    public void getAllCars_whenCarsExist_returnsCarList() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(car));

        List<CarResponseDTO> result = carService.getAllCars();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Ford", result.get(0).getMake());
    }

    @Test
    public void getAllCars_whenNoCarsExist_returnsEmptyList() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());

        List<CarResponseDTO> result = carService.getAllCars();

        assertTrue(result.isEmpty());
    }

    @Test
    public void getAllCars_whenRepositoryThrowsException_throwsException() {
        when(carRepository.findAll()).thenThrow(new DataAccessException("Database error") {});
        assertThrows(DataAccessException.class, () -> carService.getAllCars());
    }

    @Test
    public void addCar_savesCar_returnsCarResponseDTO() {
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarResponseDTO result = carService.addCar(carDTO);

        assertNotNull(result);
        assertEquals("Ford", result.getMake());
        verify(carRepository).save(any(Car.class));
    }

    @Test
    public void addCar_whenRepositoryThrowsException_throwsException() {
        when(carRepository.save(any(Car.class))).thenThrow(new DataAccessException("Database error") {});

        assertThrows(DataAccessException.class, () -> carService.addCar(carDTO));
    }

}

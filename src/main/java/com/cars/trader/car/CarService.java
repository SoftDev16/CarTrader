package com.cars.trader.car;

import java.util.List;

public interface CarService {

    List<CarResponseDTO> getAllCars();

    CarResponseDTO addCar(CarDTO carDTO);
}
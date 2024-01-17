package com.cars.trader.car;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    final
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponseDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public CarResponseDTO addCar(@RequestBody CarDTO carDTO) {
        return carService.addCar(carDTO);
    }

}

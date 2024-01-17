package com.cars.trader.car;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    final
    CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * @return A list of car response DTOs (that encapsulate data and obscure sensitive fields)
     */
    /*
    @Override
    public List<CarResponseDTO> getAllCars() {

        List<Car> carList = new ArrayList<>();
        List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

        for (Car car: carList
             ) {
            carResponseDTOList.add(new CarResponseDTO(car.getMake(), car.getModel(), car.getYear(), car.getColour(), car.getPrice(), car.getImageUrl(), car.getRepair()));
        }

        return carResponseDTOList;
    }*/

    /**
     * @return A list of car response DTOs (that encapsulate data and obscure sensitive fields)
     */
    @Override
    public List<CarResponseDTO> getAllCars() {
        List<Car> carList = carRepository.findAll();
        return mapCarsToCarResponseDTOs(carList);
    }

    /**
     * Adds a new car to the repository.
     *
     * @param carDTO the DTO containing car data to be added.
     * @return the CarResponseDTO representing the added car.
     */
    @Override
    public CarResponseDTO addCar(CarDTO carDTO) {
        Car car = convertToEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return convertToDTO(savedCar);
    }

    private Car convertToEntity(CarDTO carDTO) {
        Car car = new Car();

        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setColour(carDTO.getColour());
        car.setPrice(carDTO.getPrice());
        car.setVehicleIdentificationNumber(carDTO.getVehicleIdentificationNumber());
        car.setYear(carDTO.getYear());
        car.setImageUrl(carDTO.getImageUrl());

        return car;
    }

    private List<CarResponseDTO> mapCarsToCarResponseDTOs(List<Car> carList) {
        List<CarResponseDTO> carResponseDTOList = new ArrayList<>();
        for (Car car : carList) {
            carResponseDTOList.add(convertToDTO(car));
        }
        return carResponseDTOList;
    }

    private CarResponseDTO convertToDTO(Car car) {
        return new CarResponseDTO(car.getMake(), car.getModel(), car.getYear(), car.getColour(), car.getPrice(), car.getImageUrl());
    }
}

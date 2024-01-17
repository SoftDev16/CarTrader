package com.cars.trader.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "colour")
    private String colour;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "vehicle_identification_number", unique = true)
    private String vehicleIdentificationNumber;

    @Column(name = "image_url")
    private String imageUrl;

}
package com.cars.trader.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CarResponseDTO {

    private String make;

    private String model;

    private Integer year;

    private String colour;

    private BigDecimal price;

    private String imageUrl;

}

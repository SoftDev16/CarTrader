package com.cars.trader.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Year is required")
    @Min(value = 1885, message = "Year must be after 1885")
    @Max(value = 2100, message = "Year must be before 2100")
    private Integer year;

    @NotBlank(message = "Colour is required")
    private String colour;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @NotBlank(message = "VIN is required")
    @Size(min = 17, max = 17, message = "VIN must be exactly 17 characters")
    private String vehicleIdentificationNumber;

    @Pattern(regexp = "^https:\\/\\/cartrader\\.s3\\.eu-west-1\\.amazonaws\\.com\\/[a-zA-Z0-9_\\-]+\\.png$")
    private String imageUrl;

}

package com.ra.projectmd3test.model.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetailRequest {
   @Min(value=0, message = "Price must be greater or equal than 0")
   @NotNull(message = "Price can't be null")
    private Double price;
   @NotNull(message = "Quantity can't be null")
    private Integer quantity;
    @NotNull(message = "Size cant be null")
    private Integer size;
    @NotNull(message = "Color cant be null")
    private Integer color;
    private Integer product;
}

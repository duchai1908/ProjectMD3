package com.ra.projectmd3test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetailRequest {
   @Min(value=1, message = "Price must be greater or equal than 1")
    private Double price;
    private Integer quantity;
    private List<MultipartFile> image;
    private Integer sizeId;
    private Integer colorId;
    private Integer productId;
}

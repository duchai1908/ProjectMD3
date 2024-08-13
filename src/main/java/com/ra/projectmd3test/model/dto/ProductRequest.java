package com.ra.projectmd3test.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Product name can't be null")
    private String name;
    @NotNull(message = "You must select Category")
    private Integer category_id;
    @Column(name="description")
    private String description;
    private MultipartFile mainImage;

}

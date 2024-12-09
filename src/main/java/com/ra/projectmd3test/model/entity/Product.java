package com.ra.projectmd3test.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true,name = "product_name", length = 100)
    @NotBlank(message = "Product name can't be null")
    private String name;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    private String description;
    private String mainImage;
    @Column(name="selled")
    private Integer selled;
    @Column(name="status")
    private Boolean status;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
}

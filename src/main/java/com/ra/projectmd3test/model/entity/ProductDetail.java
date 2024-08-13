package com.ra.projectmd3test.model.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Integer quantity;
    private String image;
    @ManyToOne
    @JoinColumn(name="size_id")
    private Size size;
    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}

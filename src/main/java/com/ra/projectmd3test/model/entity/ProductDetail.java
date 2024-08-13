package com.ra.projectmd3test.model.entity;

import lombok.*;
import javax.persistence.*;


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
    @Column(name="price")
    private Double price;
    @Column(name="quantity")
    private Integer quantity;
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

package com.ra.projectmd3test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductDetail productDetail;
    @Column(name="product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    @Column(name="quantity",nullable = false)
    private Integer quantity;
}

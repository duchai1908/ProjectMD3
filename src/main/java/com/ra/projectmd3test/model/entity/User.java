package com.ra.projectmd3test.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private Date created_At;
    private Date updated_At;
    @ManyToMany
    @JoinTable(name="wishlist"
            ,joinColumns = @JoinColumn(name="user_id")
            ,inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> products;
}

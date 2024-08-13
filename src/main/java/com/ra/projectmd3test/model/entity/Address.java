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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="full_address")
    private String address;
    @Column(name="phone",length = 15)
    private String phone;
    @Column(name="receive_name",length = 50)
    private String receiveName;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}

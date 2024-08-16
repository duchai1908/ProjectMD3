package com.ra.projectmd3test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    //random code
    @Column(name = "code")
    private String code;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "note")
    private String note;
    @Column(name = "receive_name")
    private String receiveName;
    @Column(name = "receive_address")
    private String receiveAddress;
    @Column(name = "receive_phone")
    private String receivePhone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "received_at")
    private Date receivedAt;
}

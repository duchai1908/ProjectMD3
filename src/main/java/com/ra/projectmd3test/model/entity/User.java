package com.ra.projectmd3test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role"
            ,joinColumns = @JoinColumn(name="user_id")
            ,inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;
}

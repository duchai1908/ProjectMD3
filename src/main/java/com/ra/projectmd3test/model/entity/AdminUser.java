package com.ra.projectmd3test.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private Integer id;
    @Column(name="admin_name",length=100,unique=true)
    private String name;
    @Column(name = "admin_password",length=100)
    private String password;
    @Column(name="status")
    private Boolean status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="admin_role"
            ,joinColumns = @JoinColumn(name="admin_id")
            ,inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;
}

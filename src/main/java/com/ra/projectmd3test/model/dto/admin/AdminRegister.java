package com.ra.projectmd3test.model.dto.admin;

import com.ra.projectmd3test.validator.AdminNameUnique;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminRegister {
    @Column(name="admin_name",length=100,unique=true)
    @NotBlank(message = "Admin name cant be null")
    @AdminNameUnique
    private String name;
    @NotBlank(message = "Admin password cant be null")
    private String password;
    @NotNull(message = "Admin role cant be null")
    private Integer role;
}

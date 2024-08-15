package com.ra.projectmd3test.model.dto.user;

import com.ra.projectmd3test.validator.UserEmailValid;
import com.ra.projectmd3test.validator.UserNameUnique;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegister {
    @NotBlank(message = "User name cant be null")
    @UserNameUnique
    private String userName;
    @NotBlank(message = "Email cant be null")
    @UserEmailValid
    private String email;
    @NotBlank(message = "Full name cant be null")
    private String fullName;
    @NotBlank(message = "Password cant be null")
    private String password;
}

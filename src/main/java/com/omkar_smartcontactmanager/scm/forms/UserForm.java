package com.omkar_smartcontactmanager.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3,message = "Min 3 Characters is required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min=6, message = "Min 6 characters required")
    private String password;
    @NotBlank(message = "Something about yourself required")
    private String about;

    @NotBlank
    @Size(min=8, max=12, message = "Invalid Phone Number")
    private String phoneNumber;

}

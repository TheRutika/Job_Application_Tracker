package com.Rutika.job_application_tracker.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto
{
    @NotBlank(message = "name should not be blank")
    private String name;

    @Email
    @NotBlank(message = "valid email should required")
    private String email;

    @NotBlank(message = "password should required")
    private String password;
}

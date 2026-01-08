package com.Rutika.job_application_tracker.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateApplicationDto
{
    @NotBlank(message = "Company name is required")
    private String companyName;
    @NotBlank(message = "position name is required")
    private String position;

    @NotBlank
    @Email(message = "Invalid email format")
    private String hrEmail;

    @Size(max = 300, message = "notes can not exceed 300 character")
    private String notes;
}

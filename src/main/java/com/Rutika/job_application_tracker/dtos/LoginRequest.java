package com.Rutika.job_application_tracker.dtos;

import lombok.Data;

@Data
public class LoginRequest
{
    private String email;
    private String password;
}

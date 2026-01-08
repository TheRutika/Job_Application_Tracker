package com.Rutika.job_application_tracker.dtos;

import com.Rutika.job_application_tracker.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    private String name;
    private String email;
    private Role role;
}

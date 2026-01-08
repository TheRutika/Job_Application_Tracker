package com.Rutika.job_application_tracker.services;

import com.Rutika.job_application_tracker.dtos.CreateUserDto;
import com.Rutika.job_application_tracker.dtos.UserDto;
import com.Rutika.job_application_tracker.entities.User;

import java.util.List;

public interface UserService
{
    UserDto createUser(CreateUserDto user);

//    UserDto convertToDto(User user);

    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userID);
}

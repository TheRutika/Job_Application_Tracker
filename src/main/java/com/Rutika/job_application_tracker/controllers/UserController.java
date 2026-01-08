package com.Rutika.job_application_tracker.controllers;

import com.Rutika.job_application_tracker.dtos.CreateUserDto;
import com.Rutika.job_application_tracker.dtos.UserDto;
import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid CreateUserDto dto)
    {
         return userService.createUser(dto);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId)
    {
        return userService.getUserById(userId);
    }

    @GetMapping
    List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
    }

}

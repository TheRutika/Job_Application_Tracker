package com.Rutika.job_application_tracker.services.Impl;

import com.Rutika.job_application_tracker.dtos.CreateUserDto;
import com.Rutika.job_application_tracker.dtos.UserDto;
import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.enums.Role;
import com.Rutika.job_application_tracker.exceptions.EmailAlreadyExistException;
import com.Rutika.job_application_tracker.exceptions.UserNotFoundException;
import com.Rutika.job_application_tracker.repositories.UserRepository;
import com.Rutika.job_application_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserDto convertToDto(User user)
    {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }

    @Override
    public UserDto createUser(CreateUserDto dto)
    {
        if(userRepository.existsByEmail(dto.getEmail()))
        {
            throw  new EmailAlreadyExistException("Email Already Exist");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.APPLICANT);

        User savedUser =  userRepository.save(user);

        return convertToDto(savedUser);

    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));

        return convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepository.findAll();

        return users.stream().map(this::convertToDto).toList();
    }

    @Override
    public void deleteUser(Long userID) {
        if(!userRepository.existsById(userID))
        {
            throw new UserNotFoundException("User does not exist");
        }
        userRepository.deleteById(userID);
    }

}

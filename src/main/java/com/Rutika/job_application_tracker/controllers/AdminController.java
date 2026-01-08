package com.Rutika.job_application_tracker.controllers;

import com.Rutika.job_application_tracker.dtos.UserDto;
import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController
{

    private final UserRepository userRepository;
    @Autowired
    private jakarta.persistence.EntityManager entityManager;


    @GetMapping("/debug-db")
    public void debugDb() {
        String db = (String) entityManager
                .createNativeQuery("SELECT DATABASE()")
                .getSingleResult();

        Long count = ((Number) entityManager
                .createNativeQuery("SELECT COUNT(*) FROM user")
                .getSingleResult()).longValue();

        System.out.println("CONNECTED DB = " + db);
        System.out.println("USER COUNT   = " + count);
    }


    public AdminController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all_user")
    public ResponseEntity<List<UserDto>> getAllUser()
    {

        System.out.println("ADMIN CONTROLLER HIT");

        List<User> users = userRepository.findAll();
        List<UserDto> dto = users.stream().map(u->
                new UserDto(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getRole()
                )).toList();

        System.out.println("DTO SIZE = " + dto.size());

//        if(dto.isEmpty())
//        {
//            return ResponseEntity.noContent().build();
//        }




        return ResponseEntity.ok(dto);
    }

}

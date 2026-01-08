package com.Rutika.job_application_tracker.security.userdetails;

import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.enums.Role;
import com.Rutika.job_application_tracker.repositories.UserRepository;
import com.Rutika.job_application_tracker.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;



    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username){

        User user =  userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found" + username));

        return new CustomUserDetails(user);

    }
}

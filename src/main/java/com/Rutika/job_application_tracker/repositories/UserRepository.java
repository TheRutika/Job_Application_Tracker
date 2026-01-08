package com.Rutika.job_application_tracker.repositories;

import com.Rutika.job_application_tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}

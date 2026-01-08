package com.Rutika.job_application_tracker.repositories;

import com.Rutika.job_application_tracker.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<JobApplication,Long>
{

    List<JobApplication> findByUser_Id(Long userId);
}

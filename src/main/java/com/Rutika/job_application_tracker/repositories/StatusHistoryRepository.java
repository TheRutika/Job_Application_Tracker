package com.Rutika.job_application_tracker.repositories;

import com.Rutika.job_application_tracker.entities.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory,Long> {
    List<StatusHistory> findByJobApplication_applicationId(Long applicationId);
}


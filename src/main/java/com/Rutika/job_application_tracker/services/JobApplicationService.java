package com.Rutika.job_application_tracker.services;

import com.Rutika.job_application_tracker.dtos.CreateApplicationDto;
import com.Rutika.job_application_tracker.dtos.JobApplicationDto;
import com.Rutika.job_application_tracker.entities.User;

import java.util.List;

public interface JobApplicationService
{
   JobApplicationDto createApplication(CreateApplicationDto req);
   List<JobApplicationDto> getApplicationsByUser();
   JobApplicationDto updateStatus(Long applicationId, String newStatus, Integer newRound);
   JobApplicationDto getApplicationById(Long applicationId);
   void deleteApplication(Long applicationId);

   List<JobApplicationDto> getAll();


}

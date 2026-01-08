package com.Rutika.job_application_tracker.services.Impl;

import com.Rutika.job_application_tracker.dtos.CreateApplicationDto;
import com.Rutika.job_application_tracker.dtos.JobApplicationDto;
import com.Rutika.job_application_tracker.entities.JobApplication;
import com.Rutika.job_application_tracker.entities.StatusHistory;
import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.exceptions.ApplicationNotFound;
import com.Rutika.job_application_tracker.exceptions.UserNotFoundException;
import com.Rutika.job_application_tracker.repositories.ApplicationRepository;
import com.Rutika.job_application_tracker.repositories.StatusHistoryRepository;
import com.Rutika.job_application_tracker.repositories.UserRepository;
import com.Rutika.job_application_tracker.security.CustomUserDetails;
import com.Rutika.job_application_tracker.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private StatusHistoryRepository statusHistoryRepository;

    public JobApplicationDto convertToDTO(JobApplication app) {
        JobApplicationDto dto = new JobApplicationDto();

        dto.setApplicationId(app.getApplicationId());
        dto.setCompanyName(app.getCompanyName());
        dto.setPosition(app.getPosition());
        dto.setStatus(app.getStatus());
        dto.setCurrentRound(app.getCurrentRound());
        dto.setHrEmail(app.getHrEmail());
        dto.setNotes(app.getNotes());
        dto.setAppliedDate(app.getAppliedDate());
        dto.setLastUpdatedDate(app.getLastUpdatedDate());

        dto.setUserId(app.getUser().getId());
        dto.setUserName(app.getUser().getName());

        return dto;
    }



    @Override
    public JobApplicationDto createApplication(CreateApplicationDto req)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!!"));

        JobApplication application = new JobApplication();

        // 👉 Set data from DTO (user input)
        application.setCompanyName(req.getCompanyName());
        application.setPosition(req.getPosition());
        application.setHrEmail(req.getHrEmail());
        application.setNotes(req.getNotes());

        // 👉 Set system-controlled values (not from client)
        application.setUser(user);
        application.setStatus("APPLIED");
        application.setCurrentRound(1);
        application.setAppliedDate(LocalDateTime.now());
        application.setLastUpdatedDate(LocalDateTime.now()); // optional

        // 👉 Save entity
        JobApplication saved = applicationRepository.save(application);
        return convertToDTO(saved);
    }

    @Override
    public List<JobApplicationDto> getApplicationsByUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        userRepository.findById(user.getUserId()).orElseThrow(()->new UserNotFoundException("User Not Found"));

        List<JobApplication> apps = applicationRepository.findByUser_Id(user.getUserId());

        return apps.stream().map(this :: convertToDTO).toList();
    }

    @Override
    public JobApplicationDto updateStatus(Long applicationId, String newStatus, Integer newRound) {
        JobApplication application = applicationRepository.findById(applicationId).orElseThrow(()->new ApplicationNotFound("Application not found"));

        // 2️⃣ Save old status BEFORE updating
        String oldStatus = application.getStatus();

        if(oldStatus.equalsIgnoreCase("REJECTED"))
        {
            throw new RuntimeException("Applicant already rejected you can not update status further");
        }

        if(newStatus.equalsIgnoreCase("REJECTED") && !newRound.equals(application.getCurrentRound()))
        {
            throw new RuntimeException("Round cannot be changed when marking REJECTED.");
        }

        // 3️⃣ Update JobApplication (current state)
        application.setStatus(newStatus);
        application.setCurrentRound(newRound);
        application.setLastUpdatedDate(LocalDateTime.now());

        StatusHistory history = new StatusHistory();

        history.setJobApplication(application);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setRoundNumbers(newRound);
        history.setUpdatedDate(LocalDateTime.now());

        statusHistoryRepository.save(history);

        JobApplication app =  applicationRepository.save(application);

        return convertToDTO(app);
    }

    @Override
    public JobApplicationDto getApplicationById(Long applicationId)
    {
       JobApplication app =  applicationRepository.findById(applicationId).orElseThrow(()->new ApplicationNotFound("Application Not Found"));

       return convertToDTO(app);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        if(!applicationRepository.existsById(applicationId))
        {
            throw new ApplicationNotFound("Application is not found!");
        }
        applicationRepository.deleteById(applicationId);

    }

    @Override
    public List<JobApplicationDto> getAll()
    {
        List<JobApplication> apps = applicationRepository.findAll();

        return apps.stream().map(this :: convertToDTO).toList();
    }


}

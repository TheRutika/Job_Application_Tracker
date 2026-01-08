package com.Rutika.job_application_tracker.controllers;

import com.Rutika.job_application_tracker.dtos.CreateApplicationDto;
import com.Rutika.job_application_tracker.dtos.JobApplicationDto;
import com.Rutika.job_application_tracker.dtos.UpdateStatusDto;
import com.Rutika.job_application_tracker.entities.JobApplication;
import com.Rutika.job_application_tracker.services.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class JobApplicationController
{
    @Autowired
    private JobApplicationService jobApplicationService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<JobApplicationDto> createApplication(@RequestBody @Valid CreateApplicationDto req)
    {
        JobApplicationDto app =  jobApplicationService.createApplication(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(app);
    }

    @GetMapping("/user")
    public ResponseEntity<List<JobApplicationDto>> getApplicationsByUser()
    {
        List<JobApplicationDto> app =  jobApplicationService.getApplicationsByUser();

        return ResponseEntity.ok(app);
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<JobApplicationDto> updateStatus(@PathVariable Long applicationId, @RequestBody @Valid UpdateStatusDto req)
    {
       JobApplicationDto dto = jobApplicationService.updateStatus(applicationId,req.getNewStatus(),req.getNewRound());
       return ResponseEntity.ok(dto);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<JobApplicationDto> getById(@PathVariable Long applicationId)
    {
        JobApplicationDto dto =  jobApplicationService.getApplicationById(applicationId);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteById(@PathVariable Long applicationId)
    {
        jobApplicationService.deleteApplication(applicationId);

        return ResponseEntity.ok("Application Successfully Deleted!!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getAll()
    {
        List<JobApplicationDto> list = jobApplicationService.getAll();

        return ResponseEntity.ok(list);
    }


}

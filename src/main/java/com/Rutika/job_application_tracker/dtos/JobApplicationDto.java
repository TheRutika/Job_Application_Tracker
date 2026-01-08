package com.Rutika.job_application_tracker.dtos;

import lombok.Data;

import java.time.LocalDateTime;


  //JobApplicationDto is a response (output from backend)
  //It is not received from the user, so it should never be validated.


  @Data
public class JobApplicationDto
{
    private Long applicationId;


    private String companyName;
    private String position;
    private String status;
    private Integer currentRound;
    private String hrEmail;
    private String notes;
    private LocalDateTime appliedDate;
    private LocalDateTime lastUpdatedDate;
    private Long userId;
    private String userName;

}

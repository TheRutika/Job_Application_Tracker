package com.Rutika.job_application_tracker.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusHistoryDto
{
    private Long Id;

    private String oldStatus;
    private String newStatus;
    private Integer roundNumbers;
    private LocalDateTime updatedDate;
}

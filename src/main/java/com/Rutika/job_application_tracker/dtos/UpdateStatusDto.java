package com.Rutika.job_application_tracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusDto
{
    @NotBlank(message = "status required")
    private String newStatus;
    @NotNull(message = "round is required")
    private Integer newRound;
}

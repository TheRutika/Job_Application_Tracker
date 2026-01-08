package com.Rutika.job_application_tracker.services;

import com.Rutika.job_application_tracker.dtos.StatusHistoryDto;
import com.Rutika.job_application_tracker.entities.StatusHistory;

import java.util.List;

public interface StatusHistoryService {
    List<StatusHistoryDto> getAllHistory();
    List<StatusHistoryDto> getStatusByApplicationId(Long application_id);
    StatusHistoryDto getStatusById(Long id);

}

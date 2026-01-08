package com.Rutika.job_application_tracker.services.Impl;

import com.Rutika.job_application_tracker.dtos.StatusHistoryDto;
import com.Rutika.job_application_tracker.entities.StatusHistory;
import com.Rutika.job_application_tracker.exceptions.ApplicationNotFound;
import com.Rutika.job_application_tracker.repositories.ApplicationRepository;
import com.Rutika.job_application_tracker.repositories.StatusHistoryRepository;
import com.Rutika.job_application_tracker.services.StatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusHistoryImpl implements StatusHistoryService
{
    @Autowired
    private StatusHistoryRepository statusHistoryRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    private StatusHistoryDto convertToDto(StatusHistory history)
    {
        StatusHistoryDto dto = new StatusHistoryDto();

        dto.setId(history.getId());
        dto.setOldStatus(history.getOldStatus());
        dto.setNewStatus(history.getNewStatus());
        dto.setRoundNumbers(history.getRoundNumbers());
        dto.setUpdatedDate(history.getUpdatedDate());

        return dto;
    }
    @Override
    public List<StatusHistoryDto> getAllHistory() {
        List<StatusHistory>allHistory =  statusHistoryRepository.findAll();

        return allHistory.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<StatusHistoryDto> getStatusByApplicationId(Long application_id) {
        if(!applicationRepository.existsById(application_id))
        {
            throw new ApplicationNotFound("Application is Not found For Status History");
        }

        List<StatusHistory> histories = statusHistoryRepository.findByJobApplication_applicationId(application_id);

        return histories.stream().map(this::convertToDto).toList();

    }

    @Override
    public StatusHistoryDto getStatusById(Long id) {
        StatusHistory history = statusHistoryRepository.findById(id).orElseThrow(()->new RuntimeException("Status History Not Exist for id :"+id));

        return convertToDto(history);
    }
}

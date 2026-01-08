package com.Rutika.job_application_tracker.controllers;

import com.Rutika.job_application_tracker.dtos.StatusHistoryDto;
import com.Rutika.job_application_tracker.services.StatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusHistoryController {

    @Autowired
    private StatusHistoryService statusHistoryService;

    // ✔ Get all status history records
    @GetMapping
    public ResponseEntity<List<StatusHistoryDto>> getAllHistory() {
        List<StatusHistoryDto> list = statusHistoryService.getAllHistory();
        return ResponseEntity.ok(list);
    }

    // ✔ Get single history by its id
    @GetMapping("/{id}")
    public ResponseEntity<StatusHistoryDto> getStatusById(@PathVariable Long id) {
        StatusHistoryDto dto = statusHistoryService.getStatusById(id);
        return ResponseEntity.ok(dto);
    }

    // ✔ Get history by application ID (most important)
    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<StatusHistoryDto>> getHistoryByApplication(
            @PathVariable Long applicationId
    ) {
        List<StatusHistoryDto> list =
                statusHistoryService.getStatusByApplicationId(applicationId);

        return ResponseEntity.ok(list);
    }
}

package com.Rutika.job_application_tracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for MySQL
    private Long applicationId;

    private String companyName;
    private String position;
    private String status;
    private LocalDateTime appliedDate;
    private Integer currentRound;
    private String hrEmail;
    private String notes;
    private LocalDateTime lastUpdatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore  // 👈 Prevent infinite JSON recursion
    private User user;

    @OneToMany(mappedBy = "jobApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // 👈 Avoid circular recursion in response
    private List<StatusHistory> statusHistories;

    // Automatically set dates when created & updated
    @PrePersist
    public void onCreate() {
        this.appliedDate = LocalDateTime.now();
        this.lastUpdatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdatedDate = LocalDateTime.now();
    }
}

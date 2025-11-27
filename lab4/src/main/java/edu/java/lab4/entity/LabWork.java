package edu.java.lab4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lab_works", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"course_id", "lab_number"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lab_number", nullable = false)
    private Integer labNumber;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(name = "allows_late_submission", nullable = false)
    @Builder.Default
    private Boolean allowsLateSubmission = true;

    @Column(name = "late_penalty_per_day")
    private Double latePenaltyPerDay; // Percentage (e.g., 0.10 = 10%)

    @Column(name = "max_late_days")
    private Integer maxLateDays;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "labWork", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LabSubmission> submissions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
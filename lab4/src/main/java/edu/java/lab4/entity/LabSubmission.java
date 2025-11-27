package edu.java.lab4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lab_submissions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "lab_work_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submission_url", length = 500)
    private String submissionUrl; // Link to GitHub, file storage, etc.

    @Column(length = 1000)
    private String notes;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "raw_grade")
    private Double rawGrade; // Grade before penalties

    @Column(name = "final_grade")
    private Double finalGrade; // Grade after penalties

    @Column(name = "is_late")
    private Boolean isLate;

    @Column(name = "penalty_applied")
    private Double penaltyApplied; // Percentage of penalty applied

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

    @Column(name = "grader_notes", length = 1000)
    private String graderNotes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_work_id", nullable = false)
    private LabWork labWork;

    @PrePersist
    protected void onCreate() {
        if (submittedAt == null) {
            submittedAt = LocalDateTime.now();
        }
        if (labWork != null) {
            isLate = submittedAt.isAfter(labWork.getDeadline());
        }
    }
}
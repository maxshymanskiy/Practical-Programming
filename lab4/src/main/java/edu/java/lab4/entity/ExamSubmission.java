package edu.java.lab4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exam_submissions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "exam_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task assignedTask; // The variant assigned to this student

    @Column(name = "submission_url", length = 500)
    private String submissionUrl;

    @Column(length = 1000)
    private String answer;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column
    private Double grade;

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

    @Column(name = "grader_notes", length = 1000)
    private String graderNotes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
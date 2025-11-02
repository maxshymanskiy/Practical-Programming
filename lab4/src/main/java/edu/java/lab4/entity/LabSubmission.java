package edu.java.lab4.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "lab_submissions")
@Data
@NoArgsConstructor
public class LabSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_work_id", nullable = false)
    private LabWork labWork;

    @Column(nullable = false)
    private int points;

    @Column(nullable = false)
    private LocalDate submissionDate;

    public LabSubmission(Student student, LabWork labWork, int points, LocalDate submissionDate) {
        this.student = student;
        this.labWork = labWork;
        this.points = points;
        this.submissionDate = submissionDate;
    }
}

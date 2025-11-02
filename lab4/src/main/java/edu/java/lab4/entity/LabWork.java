package edu.java.lab4.entity;

import edu.java.lab4.constant.GradingConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lab_works")
@Getter
@Setter
@NoArgsConstructor
public class LabWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String labId;

    private String title;
    private int maxPoints;
    private LocalDate deadline;
    private int penaltyPerDay = GradingConstants.DEFAULT_PENALTY_PER_DAY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public LabWork(String labId, String title, int maxPoints, LocalDate deadline, int penaltyPerDay) {
        this.labId = labId;
        this.title = title;
        this.maxPoints = maxPoints;
        this.deadline = deadline;
        this.penaltyPerDay = penaltyPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return Objects.equals(labId, labWork.labId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labId);
    }
}
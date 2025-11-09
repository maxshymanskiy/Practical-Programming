package edu.java.lab4.service;

import edu.java.lab4.dto.response.JournalResponse;

public interface GradeService {

    JournalResponse calculateCourseJournal(Long courseId);

    Double calculateStudentTotalGrade(Long courseId, Long studentId);
}
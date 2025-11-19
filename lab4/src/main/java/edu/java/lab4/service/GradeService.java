package edu.java.lab4.service;

import edu.java.lab4.dto.response.JournalDto;

public interface GradeService {

    JournalDto calculateCourseJournal(Long courseId);

    Double calculateStudentTotalGrade(Long courseId, Long studentId);
}
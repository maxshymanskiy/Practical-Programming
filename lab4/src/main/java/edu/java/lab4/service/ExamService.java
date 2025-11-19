package edu.java.lab4.service;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import java.util.List;

public interface ExamService {

    ExamDto createExam(ExamCreateDto request);

    ExamDto getExamById(Long id);

    List<ExamDto> getExamsByCourse(Long courseId);

    void deleteExam(Long id);

    TaskDto createTask(TaskCreateDto request);

    List<TaskDto> getTasksByExam(Long examId);

    ExamSubmissionDto submitExam(ExamSubmissionCreateDto request);

    ExamSubmissionDto gradeExamSubmission(ExamGradeDto request);

    List<ExamSubmissionDto> getSubmissionsByExam(Long examId);
}
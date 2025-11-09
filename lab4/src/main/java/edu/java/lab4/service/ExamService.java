package edu.java.lab4.service;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import java.util.List;

public interface ExamService {

    ExamResponse createExam(ExamCreateRequest request);

    ExamResponse getExamById(Long id);

    List<ExamResponse> getExamsByCourse(Long courseId);

    void deleteExam(Long id);

    TaskResponse createTask(TaskCreateRequest request);

    List<TaskResponse> getTasksByExam(Long examId);

    ExamSubmissionResponse submitExam(ExamSubmissionRequest request);

    ExamSubmissionResponse gradeExamSubmission(ExamGradeRequest request);

    List<ExamSubmissionResponse> getSubmissionsByExam(Long examId);
}
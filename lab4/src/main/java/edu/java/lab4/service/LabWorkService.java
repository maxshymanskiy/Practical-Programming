package edu.java.lab4.service;

import edu.java.lab4.dto.request.LabGradeRequest;
import edu.java.lab4.dto.request.LabSubmissionRequest;
import edu.java.lab4.dto.request.LabWorkCreateRequest;
import edu.java.lab4.dto.response.LabSubmissionResponse;
import edu.java.lab4.dto.response.LabWorkResponse;
import java.util.List;

public interface LabWorkService {

    LabWorkResponse createLabWork(LabWorkCreateRequest request);

    LabWorkResponse getLabWorkById(Long id);

    List<LabWorkResponse> getLabWorksByCourse(Long courseId);

    void deleteLabWork(Long id);

    LabSubmissionResponse submitLabWork(LabSubmissionRequest request);

    LabSubmissionResponse gradeLabSubmission(LabGradeRequest request);

    List<LabSubmissionResponse> getSubmissionsByLabWork(Long labWorkId);

    List<LabSubmissionResponse> getSubmissionsByStudent(Long studentId);
}
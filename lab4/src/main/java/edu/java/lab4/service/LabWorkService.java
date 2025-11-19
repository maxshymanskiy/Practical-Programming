package edu.java.lab4.service;

import edu.java.lab4.dto.request.LabGradeEvaluateDto;
import edu.java.lab4.dto.request.LabSubmissionCreateDto;
import edu.java.lab4.dto.request.LabWorkCreateDto;
import edu.java.lab4.dto.response.LabSubmissionDto;
import edu.java.lab4.dto.response.LabWorkDto;
import java.util.List;

public interface LabWorkService {

    LabWorkDto createLabWork(LabWorkCreateDto request);

    LabWorkDto getLabWorkById(Long id);

    List<LabWorkDto> getLabWorksByCourse(Long courseId);

    void deleteLabWork(Long id);

    LabSubmissionDto submitLabWork(LabSubmissionCreateDto request);

    LabSubmissionDto gradeLabSubmission(LabGradeEvaluateDto request);

    List<LabSubmissionDto> getSubmissionsByLabWork(Long labWorkId);

    List<LabSubmissionDto> getSubmissionsByStudent(Long studentId);
}
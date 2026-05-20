package com.example.submissionservice.services;

import com.example.submissionservice.dto.CodeSubmissionReqDto;
import com.example.submissionservice.dto.SubmissionResDto;
import com.example.submissionservice.entity.Submission;
import com.example.submissionservice.entity.SubmissionStatus;
import com.example.submissionservice.repository.SubmissionRepository;
import com.example.submissionservice.util.FileValidationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public SubmissionResDto submitCode(CodeSubmissionReqDto reqDto) {

        // basic file validation
        FileValidationUtil.validate(reqDto.getFile());

        // create entity
        Submission submission = new Submission();

        submission.setSystemId(UUID.randomUUID().toString());

        submission.setTeamName(reqDto.getTeamName());

        submission.setLanguage(reqDto.getLanguage());

        submission.setSubmittedAt(LocalDateTime.now());

        submission.setStatus(SubmissionStatus.UPLOADED);

        // save in DB
        submissionRepository.save(submission);

        // later:
        // call sandbox-service using gRPC

        // response to frontend
        SubmissionResDto response = new SubmissionResDto();

        response.setSystemId(submission.getSystemId());

        response.setStatus(submission.getStatus().name());

        response.setMessage("Submission uploaded successfully");

        return response;
    }
}
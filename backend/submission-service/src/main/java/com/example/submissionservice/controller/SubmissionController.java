package com.example.submissionservice.controller;

import com.example.submissionservice.dto.CodeSubmissionReqDto;
import com.example.submissionservice.dto.SubmissionResDto;
import com.example.submissionservice.services.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<SubmissionResDto> submitCode(
            @ModelAttribute CodeSubmissionReqDto reqDto
    ) {

        SubmissionResDto response =
                submissionService.submitCode(reqDto);

        return ResponseEntity.ok(response);
    }
}
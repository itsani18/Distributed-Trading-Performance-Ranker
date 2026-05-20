package com.example.submissionservice.dto;

import org.springframework.web.multipart.MultipartFile;

public class CodeSubmissionReqDto {

    private String teamName;

    private String language;

    private MultipartFile file;

    public CodeSubmissionReqDto() {
    }

    public CodeSubmissionReqDto(String teamName, String language, MultipartFile file) {
        this.teamName = teamName;
        this.language = language;
        this.file = file;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
package com.example.submissionservice.dto;

public class SubmissionResDto {

    private String systemId;

    private String status;

    private String message;

    public SubmissionResDto() {
    }

    public SubmissionResDto(
            String systemId,
            String status,
            String message
    ) {
        this.systemId = systemId;
        this.status = status;
        this.message = message;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
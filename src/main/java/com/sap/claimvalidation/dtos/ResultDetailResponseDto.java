package com.sap.claimvalidation.dtos;

import javax.persistence.Column;

public class ResultDetailResponseDto {

    private String id;

    @Column
    private String severity;

    @Column
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageClassId() {
        return messageClassId;
    }

    public void setMessageClassId(String messageClassId) {
        this.messageClassId = messageClassId;
    }

    @Column
    private String messageClassId;
}

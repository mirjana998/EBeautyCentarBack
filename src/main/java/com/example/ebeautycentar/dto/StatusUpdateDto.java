package com.example.ebeautycentar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StatusUpdateDto implements Serializable {
    private String status;

    @JsonCreator
    public StatusUpdateDto(@JsonProperty("status") String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

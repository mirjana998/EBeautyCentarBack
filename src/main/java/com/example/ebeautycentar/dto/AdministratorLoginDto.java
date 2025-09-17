package com.example.ebeautycentar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AdministratorLoginDto implements Serializable {

    private String username;
    private String password;

    @JsonCreator
    public AdministratorLoginDto(@JsonProperty("username") String username,
                                 @JsonProperty("password") String password
    ) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

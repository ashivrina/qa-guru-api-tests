package com.github.ashivrina.models.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationLoginResponse {
    private String token;
    private Integer id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegistrationLoginResponse() {
    }

    public RegistrationLoginResponse(String token, Integer id) {
        this.token = token;
        this.id = id;
    }
}

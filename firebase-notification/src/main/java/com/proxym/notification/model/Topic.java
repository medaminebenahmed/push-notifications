package com.proxym.notification.model;

import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class Topic {
    private String to;
    private List<String> registration_tokens;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<String> getRegistration_tokens() {
        return registration_tokens;
    }

    public void setRegistration_tokens(List<String> registration_tokens) {
        this.registration_tokens = registration_tokens;
    }
}

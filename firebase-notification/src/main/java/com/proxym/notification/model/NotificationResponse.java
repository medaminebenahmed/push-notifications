package com.proxym.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationResponse {

    private String multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;


    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(int canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

}

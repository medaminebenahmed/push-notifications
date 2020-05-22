package com.proxym.notification.model;

import com.proxym.notification.utils.OperationEnum;

import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class Group {

    private OperationEnum operation;
    private String notification_key_name;
    private String notification_key;
    private List<String> registration_ids;


    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public String getNotification_key_name() {
        return notification_key_name;
    }

    public void setNotification_key_name(String notification_key_name) {
        this.notification_key_name = notification_key_name;
    }

    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    public String getNotification_key() {
        return notification_key;
    }

    public void setNotification_key(String notification_key) {
        this.notification_key = notification_key;
    }
}

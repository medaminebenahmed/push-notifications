package com.utils;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public enum NotificationType {

    PUSH(1,"Only Push"),
    SMS(2,"Only SMS"),
    PUSH_SMS(3,"Push and SMS");


    private int code;
    private String description;

    NotificationType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

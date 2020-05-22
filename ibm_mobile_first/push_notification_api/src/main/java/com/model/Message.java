package com.model;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class Message {

    private String alert;

    public Message(String alert) {
        this.alert = alert;
    }

    public Message() {
    }

    public String getAlert() {
        return this.alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}

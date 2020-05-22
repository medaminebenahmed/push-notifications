package com.proxym.notification.model;

import java.util.HashMap;

/**
 * Created by med-amine.ben-ahmed on 23/05/2019.
 */
public class FirebaseMessage {

    private Notification notification;
    private HashMap<String,String> data;
    private String to;


    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

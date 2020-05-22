package com.model;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class PushNotifObject {

    private Message message;
    private int notificationType;
    private Settings settings;
    private Target target;

    public PushNotifObject() {
        //by default send only notification
        notificationType = 1;
    }


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}

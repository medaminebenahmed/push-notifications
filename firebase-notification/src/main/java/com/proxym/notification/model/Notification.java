package com.proxym.notification.model;

import com.proxym.notification.utils.FirebaseUtils;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class Notification {
    private String title;
    private String body;
    private String sound;
    private String color;
    private String click_action;
    private String icon;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSound() {
        return FirebaseUtils.isEmptyOrNull(sound) ? "default" : sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


}

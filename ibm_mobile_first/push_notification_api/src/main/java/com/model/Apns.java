package com.model;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
 public class Apns {

    private Integer badge;
    private Integer category;
    private String iosActionKey;
    private Payload payload;
    private String sound;
    private String type;

    public Apns() {
    }

    public Apns(Integer badge, Integer category, String iosActionKey, Payload payload, String sound, String type) {
        this.badge = badge;
        this.category = category;
        this.iosActionKey = iosActionKey;
        this.payload = payload;
        this.sound = sound;
        this.type = type;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getIosActionKey() {
        return iosActionKey;
    }

    public void setIosActionKey(String iosActionKey) {
        this.iosActionKey = iosActionKey;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

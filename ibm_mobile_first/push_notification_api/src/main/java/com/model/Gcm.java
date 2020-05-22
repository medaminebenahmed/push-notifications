package com.model;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class Gcm {
    private Integer badge;
    private Integer category;
    private String collapseKey;
    private String delayWhileIdle;
    private Payload payload;
    private String priority;
    private String redact;
    private String sound;
    private Boolean sync;
    private long timeToLive;
    private String visibility;


    public Gcm(Integer badge, Integer category, String collapseKey, String delayWhileIdle, Payload payload, String priority, String redact, String sound, Boolean sync, long timeToLive, String visibility) {
        this.badge = badge;
        this.category = category;
        this.collapseKey = collapseKey;
        this.delayWhileIdle = delayWhileIdle;
        this.payload = payload;
        this.priority = priority;
        this.redact = redact;
        this.sound = sound;
        this.sync = sync;
        this.timeToLive = timeToLive;
        this.visibility = visibility;
    }

    public Gcm() {
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

    public String getCollapseKey() {
        return collapseKey;
    }

    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }

    public String getDelayWhileIdle() {
        return delayWhileIdle;
    }

    public void setDelayWhileIdle(String delayWhileIdle) {
        this.delayWhileIdle = delayWhileIdle;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRedact() {
        return redact;
    }

    public void setRedact(String redact) {
        this.redact = redact;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}

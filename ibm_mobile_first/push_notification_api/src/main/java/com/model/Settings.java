package com.model;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class Settings {

    private Apns apns;
    private Gcm gcm;
    private Wns wns;

    public Settings() {
    }

    public Apns getApns() {
        return apns;
    }

    public void setApns(Apns apns) {
        this.apns = apns;
    }

    public Gcm getGcm() {
        return gcm;
    }

    public void setGcm(Gcm gcm) {
        this.gcm = gcm;
    }

    public Wns getWns() {
        return wns;
    }

    public void setWns(Wns wns) {
        this.wns = wns;
    }
}

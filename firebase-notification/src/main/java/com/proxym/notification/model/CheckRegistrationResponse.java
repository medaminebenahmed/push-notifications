package com.proxym.notification.model;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class CheckRegistrationResponse {

    private String applicationVersion;
    private String attestStatus;
    private String application;
    private String scope;
    private String authorizedEntity;
    private String appSigner;
    private String platform;


    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getAttestStatus() {
        return attestStatus;
    }

    public void setAttestStatus(String attestStatus) {
        this.attestStatus = attestStatus;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedEntity() {
        return authorizedEntity;
    }

    public void setAuthorizedEntity(String authorizedEntity) {
        this.authorizedEntity = authorizedEntity;
    }

    public String getAppSigner() {
        return appSigner;
    }

    public void setAppSigner(String appSigner) {
        this.appSigner = appSigner;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}

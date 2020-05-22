package com.Api;

import com.model.*;
import com.utils.NotificationType;
import com.utils.TargetNameEnum;

import java.util.List;
import java.util.Map;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class PushNotificationProvider {


    private NotificationService notificationService = null;
    private PushNotifObject pushNotifObject;

    public PushNotificationProvider(String scheme, String server, String userName, String password, String applicationId, String runtime, String scope, String grantType) {
        this.notificationService = new NotificationServiceImpl(scheme, server, userName, password, applicationId, runtime, scope, grantType);
    }



    /**
     * Used to create notification object
     *
     * @param alertMsg :The alert message to be sent
     * @param notificationType : Value to indicate the channel (Push/SMS) used to send message. Allowed values are PUSH(Only Push"),SMS(Only SMS"),PUSH_SMS(Push and SMS")
     * @param targets :An array of targets that will be receive the notification.
     * @param targetNameEnum : Set of targets can be consumer users, devices, platforms, or tags. Only one of the targets can be set.
     * @return
     */
    public void createNotification(String alertMsg, NotificationType notificationType, List<String> targets, TargetNameEnum targetNameEnum) throws Exception {

        Target target = new Target();
        if (TargetNameEnum.DEVICES.name().equalsIgnoreCase(targetNameEnum.name()))
            target.setDeviceIds(targets);
        else if (TargetNameEnum.PLATFORMS.name().equalsIgnoreCase(targetNameEnum.name()))
            target.setPlatforms(targets);
        else if (TargetNameEnum.TAGS.name().equalsIgnoreCase(targetNameEnum.name()))
            target.setTagNames(targets);
        else
            target.setUserIds(targets);

        this.pushNotifObject = this.notificationService.createNotification(target, notificationType, alertMsg);
    }

    /**
     * Used to send notification
     * @return
     */
    public boolean sendNotification()throws Exception {

        String token = this.notificationService.getAccessToken();
        return this.notificationService.sendNotification(token, this.pushNotifObject);
    }

    /**
     * @param contents
     * @return
     */
    public Payload createPayload(Map<String, String> contents) {
        return new Payload(contents);
    }

    /**
     * Used to create GCM object (Google Cloud Messaging)
     *
     * @param badge : counter increment when notifications receives
     * @param category : define the category
     * @param collapseKey : collapse Key
     * @param delayWhileIdle : define the delay while idle
     * @param payload : define the payload
     * @param priority : define the priority
     * @param redact : define the redact
     * @param sound : define the sound
     * @param sync : synchronization
     * @param timeToLive : define the time to live
     * @param visibility : define the visibility
     * @return
     */
    public Gcm createGcm(Integer badge, Integer category, String collapseKey, String delayWhileIdle, Payload payload, String priority, String redact, String sound, Boolean sync, long timeToLive, String visibility) {
        return new Gcm(badge, category, collapseKey, delayWhileIdle, payload, priority, redact, sound, sync, timeToLive, visibility);
    }

    /**
     * Used to create Apns (Apple Push Notification Service )
     *
     * @param badge
     * @param category
     * @param iosActionKey
     * @param payload
     * @param sound
     * @param type
     * @return
     */
    public Apns createApns(Integer badge, Integer category, String iosActionKey, Payload payload, String sound, String type) {
        return new Apns(badge, category, iosActionKey, payload, sound, type);
    }

    /**
     * Create Wns object
     *
     * @return
     */
    public Wns createWns() {
        return new Wns();
    }

    /**
     * @param apns
     * @param gcm
     * @param wns
     */
    public void addSettings(Apns apns, Gcm gcm, Wns wns) {
        this.notificationService.addNotificationSettings(this.pushNotifObject, apns, gcm, wns);
    }

    /**
     * return the push notification object
     * @return
     */
    public PushNotifObject getPushNotifObject() {
        return pushNotifObject;
    }

    /**
     *  Used to set notification object
     * @param pushNotifObject
     */
    public void setPushNotifObject(PushNotifObject pushNotifObject) {
        this.pushNotifObject = pushNotifObject;
    }
}

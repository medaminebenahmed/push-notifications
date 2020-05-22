package com;

import com.Api.PushNotificationProvider;
import com.model.Apns;
import com.model.Gcm;
import com.model.Payload;
import com.model.Settings;
import com.utils.NotificationType;
import com.utils.TargetNameEnum;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ©Proxym-Group : ESS POLE <br/>
 * Created by med-amine.ben-ahmed on 21/12/2017.
 */
public class PushNotificationService {
    private static final Logger LOGGER =  Logger.getLogger(com.PushNotificationService.class.getName());
    private PushNotificationProvider pushNotificationProvider;

    /**
     * Used to initialize the push notification context<br/>
     * @param protocol : specify the used protocol
     * @param serverUrl : specify  the URL
     * @param user : define the user
     * @param pwd : define password
     * @param applicationId : define the application Id
     * @param runTime : define the runtime
     * @param scope : define scope
     * @param grantType : define grant type
     */
    public void init(String protocol, String serverUrl, String user, String pwd, String applicationId, String runTime, String scope, String grantType) {
        LOGGER.log(Level.INFO, "[com.PushNotificationService] init");
        pushNotificationProvider = new PushNotificationProvider(protocol, serverUrl, user, pwd, applicationId, runTime, scope, grantType);
    }


    /**
     * Used to create Push notification object
     * @param alertMsg : push notification message
     * @param targetNameEnum :
     * @param targets
     */
    public void createPushNotification(String alertMsg, TargetNameEnum targetNameEnum, List<String> targets) throws Exception{
        LOGGER.log(Level.INFO, "[com.PushNotificationService] createPush");
        pushNotificationProvider.createNotification(alertMsg, NotificationType.PUSH, targets, targetNameEnum);
    }



    /**
     * Used to Send Notification
     */
    public boolean sendNotification() throws Exception {
       LOGGER.log(Level.INFO, "[com.PushNotificationService] sendNotification");
        return pushNotificationProvider.sendNotification();
    }

    /**
     * Used to create Gcm section
     */
    public void createGcmSection() {
        LOGGER.log(Level.INFO, "[com.PushNotificationService] createGcmSection");
        Gcm gcm = pushNotificationProvider.createGcm(null, null, "", "false", null, null, null, null, null, 0, null);
        if (pushNotificationProvider.getPushNotifObject() != null && pushNotificationProvider.getPushNotifObject().getSettings() == null)
            pushNotificationProvider.addSettings(null, gcm, null);
        else {
            Settings settings = pushNotificationProvider.getPushNotifObject().getSettings();
            settings.setGcm(gcm);
        }
    }

    /**
     * Used to create Apns Section
     */
    public void createApnsSection() {
       LOGGER.log(Level.INFO, "[com.PushNotificationService] createApnsSection");
        Apns apns = pushNotificationProvider.createApns(1, null, "Ok", null, null, "MIXED");

        if (pushNotificationProvider.getPushNotifObject() != null && pushNotificationProvider.getPushNotifObject().getSettings() == null)
            pushNotificationProvider.addSettings(apns, null, null);
        else {
            Settings settings = pushNotificationProvider.getPushNotifObject().getSettings();
            settings.setApns(apns);
        }

    }

    /**
     * add payload to Apns section
     *
     * @param content
     */
    public void addPayloadToApns(Map<String, String> content) {
        LOGGER.log(Level.INFO, "[com.PushNotificationService] addPayloadToApns");
        Payload payload = pushNotificationProvider.createPayload(content);
        Settings settings = pushNotificationProvider.getPushNotifObject().getSettings();
        if (settings == null || settings.getApns() == null)
            createApnsSection();
        pushNotificationProvider.getPushNotifObject().getSettings().getApns().setPayload(payload);

    }


    /**
     * Used to create a payload object and add it to Gcm section
     *
     * @param content
     */
    public void addPayloadToGcm(Map<String, String> content) {
       LOGGER.log(Level.INFO, "[com.PushNotificationService] addPayloadToGcm");
        Payload payload = pushNotificationProvider.createPayload(content);
        Settings settings = pushNotificationProvider.getPushNotifObject().getSettings();
        if (settings == null || settings.getGcm() == null)
            createGcmSection();
        pushNotificationProvider.getPushNotifObject().getSettings().getGcm().setPayload(payload);

    }

    /**
     * used to add sound to Gcm section
     * @param sound
     */
    public void addSoundToGcm(String sound) {
       LOGGER.log(Level.INFO, "[com.PushNotificationService] addPayloadToGcm");
        if (pushNotificationProvider.getPushNotifObject().getSettings() != null)
            this.pushNotificationProvider.getPushNotifObject().getSettings().getGcm().setSound(sound);
        else {
            createGcmSection();
            pushNotificationProvider.getPushNotifObject().getSettings().getGcm().setSound(sound);

        }
    }
    /**
     * Used to add sound to Apns section
     * @param sound
     */
    public void addSoundToApns(String sound) {
        LOGGER.log(Level.INFO, "[com.PushNotificationService] addSoundToApns");
        if (pushNotificationProvider.getPushNotifObject().getSettings() != null)
            this.pushNotificationProvider.getPushNotifObject().getSettings().getApns().setSound(sound);
        else {
            createApnsSection();
            pushNotificationProvider.getPushNotifObject().getSettings().getApns().setSound(sound);

        }
    }


}

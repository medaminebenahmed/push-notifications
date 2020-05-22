package com.Api;

import com.model.*;
import com.utils.NotificationType;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public interface NotificationService {


    /**
     * Used to create notification
     *
     * @param target An array of targets that will be receive the notification.
     * @param notificationType Value to indicate the channel (Push/SMS) used to send message. Allowed values are PUSH(Only Push"),SMS(Only SMS"),PUSH_SMS(Push and SMS")
     * @param alertMsg notification message
     * @return
     */
    PushNotifObject createNotification(Target target, NotificationType notificationType, String alertMsg) throws Exception;


    /**
     * Used to Create Settings for given notification
     *
     * @param apns Apple Push Notification Service
     * @param gcm Google Cloud Messaging
     * @param wns Windows Push Notification Services
     * @return
     */
    void addNotificationSettings(PushNotifObject pushNotifObject,Apns apns, Gcm gcm, Wns wns);

    /**
     *Used to get accessToken
     * @return
     */
    String getAccessToken() throws Exception;

    /**
     * Used to send Notification
     * @param token
     * @param pushNotifObject
     * @return
     */
    boolean sendNotification(String token, PushNotifObject pushNotifObject) throws Exception;



}

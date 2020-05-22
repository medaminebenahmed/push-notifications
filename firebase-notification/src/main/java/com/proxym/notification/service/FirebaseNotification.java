package com.proxym.notification.service;

import com.proxym.notification.model.*;

import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public interface FirebaseNotification {


    /**
     * Used to send notification
     *
     * @param notification
     * @return
     */
    NotificationResponse sendNotification(FirebaseMessage notification)throws Exception;

    /**
     * Used to create group
     *
     * @param group
     * @param projectId
     * @return :A successful request returns a notification_key
     */
    String createGroup(Group group, String projectId)throws Exception;

    /**
     * Used to remove group
     *
     * @param group
     * @param projectId
     */
    void removeGroup(Group group, String projectId)throws Exception;

    /**
     * To add  devices from an existing group
     *
     * @param devices
     * @param groupNotificationKey
     * @param groupNotificationKeyName
     * @param projectId
     */
    void addDevicesGroup(List<String> devices, String groupNotificationKey, String groupNotificationKeyName, String projectId)throws Exception;
    /**
     * To remove  devices from an existing group
     *
     * @param devices
     * @param groupNotificationKey
     * @param groupNotificationKeyName
     * @param projectId
     */
    void removeDevicesGroup(List<String> devices, String groupNotificationKey, String groupNotificationKeyName, String projectId)throws Exception;

    /**
     * Used to create topic
     *
     * @param topic
     */
    void createTopic(Topic topic)throws Exception;

    /**
     * Used to remove topic
     *
     * @param topic
     */
    void removeTopic(Topic topic)throws Exception;


    /**
     *
     * @param topic
     * @param tokens
     */
    void addTokensToTopic(String topic, List<String> tokens)throws Exception;

    /**
     *
     * @param topic
     * @param tokens
     */
    void removeTokensFromTopic(String topic, List<String> tokens)throws Exception;

    /**
     * Used to validate given token
     *
     * @param token
     * @return
     */
    CheckRegistrationResponse validateToken(String token)throws Exception;
}

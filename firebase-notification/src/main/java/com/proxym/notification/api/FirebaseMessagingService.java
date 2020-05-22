package com.proxym.notification.api;

import com.proxym.notification.exceptions.FirebaseException;
import com.proxym.notification.model.*;
import com.proxym.notification.utils.OperationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class FirebaseMessagingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseMessagingService.class);

    com.proxym.notification.service.FirebaseNotification firebaseNotification;

    private static FirebaseMessagingService singleton = null;

    public static FirebaseMessagingService getInstance(String serverKey) {
        if (singleton == null)
            singleton = new FirebaseMessagingService(serverKey);
        return singleton;
    }

    private FirebaseMessagingService(String serverKey) {
        firebaseNotification = new com.proxym.notification.service.FireBaseNotificationImpl(serverKey);
    }

    /**
     * Used to send notification
     *
     * @param notification
     * @return
     */
    public boolean sendNotification(FirebaseMessage notification) {

        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] sendNotification :Start");
            NotificationResponse notificationResponse = firebaseNotification.sendNotification(notification);
            return notificationResponse.getFailure() != 1;
        } catch (Exception e) {
            throw new FirebaseException("An error have been occurred trying to send notification ", e);
        }
    }

    /**
     * Used to create new Group
     *
     * @param projectId
     * @param groupName
     * @param fcmTokens
     * @return
     */
    public String createGroup(String projectId, String groupName, List<String> fcmTokens) {
        String notificationKey = null;
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] createGroup :Start");
            Group group = new Group();
            group.setNotification_key_name(groupName);
            group.setOperation(OperationEnum.create);
            group.setRegistration_ids(fcmTokens);
            notificationKey = firebaseNotification.createGroup(group, projectId);
        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to create new group ", e);
            throw new FirebaseException("An error occurred trying to create new group  ", e);
        }
        return notificationKey;
    }

    /**
     * Used to remove existing devices from group
     *
     * @param projectId
     * @param groupName
     * @param groupKey
     * @param fcmTokens
     */
    public void removeDevicesGroup(String projectId, String groupName, String groupKey, List<String> fcmTokens) {
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] removeDevicesGroup :project {},groupName {}", projectId, groupKey);
            firebaseNotification.removeDevicesGroup(fcmTokens, groupKey, groupName, projectId);
        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to remove devices from group ", e);
            throw new FirebaseException("error occurred trying to remove devices from group  ", e);
        }
    }

    /**
     * Used to add new devices to group
     *
     * @param projectId
     * @param groupName
     * @param groupKey
     * @param fcmTokens
     */
    public void addDevicesGroup(String projectId, String groupName, String groupKey, List<String> fcmTokens) {
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] addDevicesGroup :project {},groupName {}", projectId, groupKey);
            firebaseNotification.addDevicesGroup(fcmTokens, groupKey, groupName, projectId);
        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to add devices to group ", e);
            throw new FirebaseException("An error occurred trying to add devices(tokens) to group   ", e);
        }
    }


    /**
     * Used to create new topic
     *
     * @param topicName
     * @param tokens
     * @return
     */
    public boolean createTopic(String topicName, List<String> tokens) {
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] createTopic :Start");
            Topic topic = new Topic();
            topic.setTo(topicName);
            if (tokens != null && !tokens.isEmpty())
                topic.setRegistration_tokens(tokens);
            firebaseNotification.createTopic(topic);
            return true;
        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to create new topic ", e);
            throw new FirebaseException("An error occurred trying to create new topic ", e);
        }

    }

    /**
     * Used to subscribe to topic
     *
     * @param topicName
     * @param tokens
     * @return
     */
    public boolean subscribeTopic(String topicName, List<String> tokens) {
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] subscribeTopic :Start");
            Topic topic = new Topic();
            topic.setTo(topicName);
            if (tokens != null && !tokens.isEmpty())
                topic.setRegistration_tokens(tokens);
            firebaseNotification.createTopic(topic);
            return true;
        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to subscribe to topic ", e);
            throw new FirebaseException("An error occurred trying to subscribe to topic", e);
        }

    }

    /**
     * Used to unSubscribe  to topic
     *
     * @param topicName
     * @param tokens
     * @return
     */
    public boolean unSubscribeTopic(String topicName, List<String> tokens) {
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] unSubscribeTopic :Start");
            firebaseNotification.removeTokensFromTopic(topicName, tokens);
            return true;
        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to unSubscribeTopic from topic ", e);
            throw new FirebaseException("An error occurred trying to unSubscribeTopic from topic", e);
        }

    }

    /**
     * Used to validate token
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            LOGGER.info("[com.proxym.notification.api.FirebaseMessagingService] validateToken {} :Start", token);
            CheckRegistrationResponse response = firebaseNotification.validateToken(token);
            return (response != null);

        } catch (Exception e) {
            LOGGER.error("[com.proxym.notification.api.FirebaseMessagingService] an error occurred trying to validate token ", e);
            throw new FirebaseException("An error occurred trying to validate token  ", e);
        }

    }

}

package com.proxym.notification.service;

import com.proxym.notification.model.*;
import com.proxym.notification.utils.FirebaseConstants;
import com.proxym.notification.utils.FirebaseUtils;
import com.proxym.notification.utils.OperationEnum;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class FireBaseNotificationImpl implements FirebaseNotification {

    private static final Logger LOGGER = LoggerFactory.getLogger(FireBaseNotificationImpl.class);

    private String serverKey;

    public FireBaseNotificationImpl(String serverKey) {
        this.serverKey = serverKey;
    }

    public NotificationResponse sendNotification(FirebaseMessage notification) throws Exception {
        LOGGER.debug("[FireBaseNotificationImpl] sendNotification :Start");
        NotificationResponse response;
        HashMap headerParams = new HashMap<>();
        headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
        headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
        HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.API_URL_FCM, headerParams, notification);
        if (httpResponse == null)
            throw new NullPointerException();
        int responseCode = httpResponse.getStatusLine().getStatusCode();
        if (responseCode == 200) {//success response status
            try {
                String responseJSON = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                response = (NotificationResponse) FirebaseUtils.convertJsonToClassObject(responseJSON, NotificationResponse.class);
            } catch (IOException e) {
                throw new Exception("Error occurred trying to send notification ", e);
            } catch (Exception e) {
                throw new Exception("Error occurred trying to send notification ", e);
            }
            LOGGER.debug("[FireBaseNotificationImpl] send Notification successfully !");
        } else
            throw new Exception("Error occurred trying to send notification");

        return response;
    }

    public String createGroup(Group group, String projectId) throws Exception {
        GroupResponse groupResponse = new GroupResponse();
        try {
            LOGGER.debug("[FireBaseNotificationImpl] createGroup :Start");
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
            headerParams.put(FirebaseConstants.PROJECT_ID, projectId);
            if (group == null)
                group = new Group();
            group.setOperation(OperationEnum.create);
            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.GROUP_URL_FCM, headerParams, group);
            if (httpResponse == null)
                throw new NullPointerException();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == 200) {//success response status
                try {
                    String responseJSON = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    groupResponse = (GroupResponse) FirebaseUtils.convertJsonToClassObject(responseJSON, GroupResponse.class);
                } catch (IOException e) {
                    throw new Exception("Error occurred trying to create new group ", e);

                } catch (Exception e) {
                    throw new Exception("Error occurred trying to create new group ", e);

                }
                LOGGER.debug("[FireBaseNotificationImpl] group created successfully !");
            } else
                throw new Exception("Error occurred trying to create new group ");

        } catch (Exception e) {
            LOGGER.error("[FireBaseNotificationImpl] An expected error have been occurred trying to create new group ", e);
            throw new Exception("Error occurred trying to create new group ", e);
        }
        return groupResponse.getNotification_key();
    }

    @Override
    public void removeGroup(Group group, String projectId) throws Exception {

    }

    @Override
    public void addDevicesGroup(List<String> devices, String groupNotificationKey, String groupNotificationKeyName, String projectId) throws Exception {
        Group group;

        try {
            LOGGER.debug("[FireBaseNotificationImpl] addDevicesGroup");
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
            headerParams.put(FirebaseConstants.PROJECT_ID, projectId);

            group = new Group();
            group.setOperation(OperationEnum.add);
            group.setNotification_key(groupNotificationKey);
            group.setNotification_key_name(groupNotificationKeyName);
            group.setRegistration_ids(devices);
            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.GROUP_URL_FCM, headerParams, group);
            if (httpResponse == null)
                throw new NullPointerException();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) //failed response status
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to add devices to group!");
            LOGGER.debug("[FireBaseNotificationImpl] devices have been added  to group successfully !");

        } catch (Exception e) {
            throw new Exception("Error occurred trying to add new device to  group ", e);
        }
    }

    @Override
    public void removeDevicesGroup(List<String> devices, String groupNotificationKey, String groupNotificationKeyName, String projectId) throws Exception {
        Group group;

        try {
            LOGGER.debug("[FireBaseNotificationImpl] removeDevicesGroup");
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
            headerParams.put(FirebaseConstants.PROJECT_ID, projectId);

            group = new Group();
            group.setOperation(OperationEnum.create);
            group.setNotification_key(groupNotificationKey);
            group.setNotification_key_name(groupNotificationKeyName);
            group.setRegistration_ids(devices);
            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.GROUP_URL_FCM, headerParams, group);
            if (httpResponse == null)
                throw new NullPointerException();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) //failed response status
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to delete devices from group!");
            LOGGER.debug("[FireBaseNotificationImpl] devices have been deleted  from group successfully !");

        } catch (Exception e) {
            throw new Exception("Error occurred trying to delete device from group ", e);
        }
    }

    public void createTopic(Topic topic) throws Exception {
        try {
            LOGGER.debug("[FireBaseNotificationImpl] createTopic");
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.CREATE_TOPIC_URL_FCM, headerParams, topic);
            if (httpResponse == null)
                throw new NullPointerException();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) //failed response status
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to create new Topic !");
//            LOGGER.debug("[FireBaseNotificationImpl] Topic created successfully !");

        } catch (Exception e) {
            throw new Exception("Error occurred trying to add new topic ", e);
        }
    }

    public void removeTopic(Topic topic) throws Exception {
        try {
            LOGGER.debug("[FireBaseNotificationImpl] removeTopic");
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.REMOVE_TOPIC_URL_FCM, headerParams, topic);
            if (httpResponse == null)
                throw new NullPointerException();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) //failed response status
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to delete devices from group!");
            LOGGER.debug("[FireBaseNotificationImpl] Topic have been removed successfully !");

        } catch (Exception e) {
            throw new Exception("Error occurred trying to remove topic ", e);
        }
    }

    public CheckRegistrationResponse validateToken(String token) throws Exception {
        CheckRegistrationResponse response = null;

        try {
            LOGGER.debug("[FireBaseNotificationImpl] CheckRegistrationResponse");
            HashMap headerParams = new HashMap<>();

            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);
            HttpResponse httpResponse = FirebaseUtils.executeGetRequest(MessageFormat.format(FirebaseConstants.VALIDATE_TOKEN_URL_FCM, token), headerParams);
            if (httpResponse == null)
                throw new NullPointerException();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == 200) {//success response status
                try {
                    String responseJSON = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    response = (CheckRegistrationResponse) FirebaseUtils.convertJsonToClassObject(responseJSON, CheckRegistrationResponse.class);
                } catch (IOException e) {
                    throw new Exception("Error occurred trying to check token ", e);
                } catch (Exception e) {
                    throw new Exception("Error occurred trying to check token  ", e);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error occurred trying to check token ", e);
        }
        return response;
    }


    @Override
    public void addTokensToTopic(String topicName, List<String> tokens) throws Exception {
        LOGGER.debug("[FireBaseNotificationImpl] addTokensToTopic");
        try {
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);

            Topic topic = new Topic();
            topic.setTo(topicName);
            if (tokens != null && !tokens.isEmpty())
                topic.setRegistration_tokens(tokens);
            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.CREATE_TOPIC_URL_FCM, headerParams, topic);
            if (httpResponse == null) {
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to add tokens to topic group!");
                throw new NullPointerException();
            }
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) //failed response status
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to add tokens to topic group!");
            else
                LOGGER.debug("[FireBaseNotificationImpl] tokens have been added successfully !");

        } catch (Exception e) {
            throw new Exception("Error occurred trying to add tokens to topic ", e);
        }
    }

    @Override
    public void removeTokensFromTopic(String topicName, List<String> tokens)throws Exception {
        LOGGER.debug("[FireBaseNotificationImpl] removeTokensFromTopic");
        try {
            HashMap headerParams = new HashMap<>();
            headerParams.put(FirebaseConstants.CONTENT_TYPE, FirebaseConstants.CONTENT_TYPE_JSON_VALUE);
            headerParams.put(FirebaseConstants.AUTHORIZATION, FirebaseConstants.KEY + "=" + serverKey);

            Topic topic = new Topic();
            topic.setTo(topicName);
            if (tokens != null && !tokens.isEmpty())
                topic.setRegistration_tokens(tokens);

            HttpResponse httpResponse = FirebaseUtils.executePostRequest(FirebaseConstants.REMOVE_TOPIC_URL_FCM, headerParams, topic);
            if (httpResponse == null) {
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to remove tokens from topic group!");
                throw new NullPointerException();
            }
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) //failed response status
                LOGGER.error("[FireBaseNotificationImpl] error have been occurred trying to remove tokens from topic group!");
            else
                LOGGER.debug("[FireBaseNotificationImpl] tokens have been removed successfully !");

        } catch (Exception e) {
            throw new Exception("Error occurred trying to remove token from topic ", e);
        }
    }
}

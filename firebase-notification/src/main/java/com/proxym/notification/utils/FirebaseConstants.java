package com.proxym.notification.utils;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class FirebaseConstants {

    public final static String TOPICS = "topics";
    public final static String KEY = "key";
    public final static String AUTHORIZATION = "Authorization";
    public final static String PROJECT_ID = "project_id";
    public final static String CONTENT_TYPE = "Content-Type";
    public final static String CONTENT_TYPE_JSON_VALUE = "application/json";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    public final static String GROUP_URL_FCM = "https://android.googleapis.com/gcm/notification";
    public final static String CREATE_TOPIC_URL_FCM = "https://iid.googleapis.com/iid/v1:batchAdd";
    public final static String REMOVE_TOPIC_URL_FCM = "https://iid.googleapis.com/iid/v1:batchRemove";
    public final static String VALIDATE_TOKEN_URL_FCM = "https://iid.googleapis.com/iid/info/{0}";

}

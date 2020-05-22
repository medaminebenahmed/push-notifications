package com.Api;

import com.model.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.utils.Constants;
import com.utils.NotificationType;
import com.utils.Utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
class NotificationServiceImpl implements NotificationService {
    private String server;
    private String scheme;
    private String userName;
    private String password;
    private String pushNotifPath;
    private String accessTokenPath;
    private String scope;
    private String grantType;


    public NotificationServiceImpl(String scheme, String server, String userName, String password, String applicationId, String runtime, String scope, String grantType) {
        this.server = server;
        this.scheme = scheme;
        this.password = password;
        this.userName = userName;
        this.scope = Utils.isEmptyOrNull(scope) ? "RegisteredClient" : scope;
        this.accessTokenPath = MessageFormat.format(Constants.ACCESS_TOKEN_PATTERN, runtime);
        this.pushNotifPath = MessageFormat.format(Constants.PUSH_NOTIFICATION_PATTERN, applicationId);
        this.grantType = Utils.isEmptyOrNull(grantType) ? "client_credentials" : grantType;
    }

    public PushNotifObject createNotification(Target target, NotificationType notificationType, String alertMsg) throws Exception {

        PushNotifObject pushNotifObject = new PushNotifObject();
        Message message = new Message(alertMsg);
        pushNotifObject.setMessage(message);
        pushNotifObject.setNotificationType(notificationType.getCode());

        if (target.verifyTarget())
            pushNotifObject.setTarget(target);
        else
            throw new Exception("More than one target have been specified , Only one can be set.");

        return pushNotifObject;
    }

    public void addNotificationSettings(PushNotifObject pushNotifObject, Apns apns, Gcm gcm, Wns wns) {
        Settings settings = new Settings();
        settings.setGcm(gcm);
        settings.setApns(apns);
        settings.setWns(wns);
        pushNotifObject.setSettings(settings);
    }

    public String getAccessToken() throws Exception {
        String encodedURL = Utils.buildURI(this.scheme, this.server, this.accessTokenPath);
        return Utils.getAccessToken(this.userName, this.password, encodedURL, this.grantType, this.scope);
    }

    public boolean sendNotification(String token, PushNotifObject pushNotifObject) throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        try {
            params.put("Content-Type", "application/json");
            params.put("Authorization", "Bearer " + token);
            String url = Utils.buildURI(this.scheme, this.server, this.pushNotifPath);
            HttpResponse httpResponse = Utils.executePostRequest(url, params, pushNotifObject);
            if (httpResponse == null)
                throw new NullPointerException();
            int ResponseCode = httpResponse.getStatusLine().getStatusCode();
            return ResponseCode == 202;
        } catch (Exception ex) {
            throw new Exception("Error occurred trying to  send notification ", ex);
        }

    }
}

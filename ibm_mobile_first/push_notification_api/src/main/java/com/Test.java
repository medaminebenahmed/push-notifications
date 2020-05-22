package com;

import com.Api.DevicesProvider;
import com.model.Device;
import com.utils.TargetNameEnum;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 21/12/2017.
 */
public class Test {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(com.Test.class);

    public static void main(String args[]) throws Exception {

//        String scheme = "https";
//        String serverPath = "oci.barwabank.com";
        String scheme = "http";
        String serverPath = "10.0.11.132:9080";
        String runtime = "BBOCUAT";
        String applicationId = "com.barwa.barwaApp";
        String userName_device = "admin";
        String userName = "barwa";
        String pwd = "admin123";
        String pwd_device = "admin123";
//        String alertMsg = "test push notification";
        String alertMsg = "سلام عليكم";
        String scope = "messages.write push.application.* devices.write";
        String grant_type = "client_credentials";


        DevicesProvider devicesProvider = new DevicesProvider(scheme, serverPath, userName_device, pwd_device, applicationId, runtime, scope, grant_type);
      List<Device> devices =  devicesProvider.getEnrolledDevices();

        System.err.println("***"+devices.size());
          Device device =          devicesProvider.getDeviceDetails("98f204b4-fba8-319c-8f24-7eb58fc93b53");
        System.err.println("***"+device.getDeviceId() + "***"+device.getCreatedTime());

        // devices Ids
        List<String> deviceIds = new ArrayList();
        deviceIds.add("98f204b4-fba8-319c-8f24-7eb58fc93b53");
//        deviceIds.add("4b3ec20a-8185-3af8-a80d-02ee22b2ec70");
        deviceIds.add("14E34BE1-228A-458F-89A1-963D3DA9BF1C");
        //PlatForm
        List<String> platformList = new ArrayList<String>();
        // platformList.add("A");//IOS
        //   platformList.add("G");//Android

        List<String> usersList = new ArrayList<String>();
        //     usersList.add("Admin");//IOS
        usersList.add("admin");//Android
        usersList.add("admin");//Android
//        usersList.add("ADMIN");//Android

        HashMap<String, String> content = new HashMap<String, String>();
        content.put("fromOci", "true");


        com.PushNotificationService pushNotificationComponent = new com.PushNotificationService();
        pushNotificationComponent.init(scheme, serverPath, userName, pwd, applicationId, runtime, scope, grant_type);

        //for PlatForm target
        pushNotificationComponent.createPushNotification(alertMsg, TargetNameEnum.DEVICES, deviceIds);

        //for Devices target
        //    pushNotificationComponent.createPushNotification(alertMsg, TargetNameEnum.DEVICES, deviceIds);
        // byte ptext[] = alertMsg.getBytes();
        //  String value =  alertMsg.getBytes("US-ASCII");
        //  pushNotificationComponent.createPushNotification("[-40, -77, -39, -124, -40, -89, -39, -123, 32, -40, -71, -39, -124, -39, -118, -39, -125, -39, -123]", TargetNameEnum.USERS, usersList);
//        pushNotificationComponent.createApnsSection();
//        // pushNotificationComponent.createGcmSection();
//        //  pushNotificationComponent.addPayloadToGcm(content);
//        //  pushNotificationComponent.addSoundToGcm("push_notif_sound.mp3");
//        pushNotificationComponent.addPayloadToApns(content);
//        pushNotificationComponent.addSoundToApns("push_notif_sound.mp3");


        System.err.println(pushNotificationComponent.sendNotification());

    }
}

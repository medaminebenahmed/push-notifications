package com;

import com.Api.DevicesProvider;
import com.model.Device;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by med-amine.ben-ahmed on 06/02/2018.
 */
public class DeviceNotificationApi {
    private static final Logger LOGGER =  Logger.getLogger(DeviceNotificationApi.class.getName());
    private DevicesProvider devicesProvider;
    /**
     * Used to initialize the context<br/>
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
        devicesProvider = new DevicesProvider(protocol, serverUrl, user, pwd, applicationId, runTime, scope, grantType);
    }

    /**
     * Used to retrieve all enrolled devices
     * @return
     */
    public List<Device> getDevices(){
        return this.devicesProvider.getEnrolledDevices();
    }

    /**
     * Used to retreive devices details
     * @param deviceId
     * @return
     */
    public Device getDeviceDetail(String deviceId){
        return this.devicesProvider.getDeviceDetails(deviceId);
    }

    /**
     * Used to delete all devices
     * @return
     */
    public boolean deleteDevices(){
       return this.devicesProvider.deleteDevices();
    }

    /**
     * Used to delete device
     * @param deviceId
     * @return
     */
    public boolean deleteDevice(String deviceId){
        return this.devicesProvider.deleteDevice(deviceId);
    }
}

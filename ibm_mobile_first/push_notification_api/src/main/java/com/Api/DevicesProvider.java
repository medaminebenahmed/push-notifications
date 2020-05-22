package com.Api;

import com.model.Device;
import com.utils.Utils;

import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 30/01/2018.
 */
public class DevicesProvider {

    DevicesService devicesService;


    public DevicesProvider(String scheme, String server, String userName, String password, String applicationId, String runtime, String scope, String grantType) {
        this.devicesService = new DeviceServiceImpl(scheme, server, userName, password, applicationId, runtime, scope, grantType);
    }

    public boolean deleteDevices() {
        boolean result = false;
        try {
            result = this.devicesService.deleteDevices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteDevice(String deviceId) {
        boolean result = false;
        try {
            result = this.devicesService.deleteDevice(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Device getDeviceDetails(String deviceId) {
        Device device = null;
        try {
        device = this.devicesService.deviceDetails(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

    public List<Device> getEnrolledDevices(){
        List<Device> devices = null;
        try {
            devices = this.devicesService.enrolledDevicesList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

}

package com.Api;

import com.model.Device;

import java.util.List;

/**
 * Created by med-amine.ben-ahmed on 30/01/2018.
 */
public interface DevicesService {


    /**
     * Used to display enrolled devices list
     * @return
     */
    List<Device> enrolledDevicesList() throws Exception;

    /**
     * Used to display Device details based on given Id
     * @param deviceId
     * @return
     */
    Device deviceDetails(String deviceId)throws Exception;

    /**
     * Used to delete Device based on his Id
     * @param deviceId
     * @return
     */
    boolean deleteDevice(String deviceId) throws Exception;

    /**
     * Used to delete all devices
     * @return
     */
     boolean deleteDevices() throws Exception;
}

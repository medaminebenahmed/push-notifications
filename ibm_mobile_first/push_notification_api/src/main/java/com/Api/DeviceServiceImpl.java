package com.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Device;
import com.utils.Constants;
import com.utils.Utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by med-amine.ben-ahmed on 30/01/2018.
 */
public class DeviceServiceImpl implements DevicesService {

    private String server;
    private String scheme;
    private String userName;
    private String password;
    private String devicesPath;
    private String accessTokenPath;
    private String scope;
    private String grantType;

    public DeviceServiceImpl(String scheme, String server, String userName, String password, String applicationId, String runtime, String scope, String grantType) {
        this.server = server;
        this.scheme = scheme;
        this.password = password;
        this.userName = userName;
        this.scope = Utils.isEmptyOrNull(scope) ? "messages.write push.application.* devices.write" : scope;
        this.accessTokenPath = MessageFormat.format(Constants.ACCESS_TOKEN_PATTERN, runtime);
        this.devicesPath = MessageFormat.format(Constants.DEVICES_PATTERN, runtime, applicationId);
        this.grantType = Utils.isEmptyOrNull(grantType) ? "client_credentials" : grantType;
    }

    public List<Device> enrolledDevicesList() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        HttpEntity responseEntity = null;
        String devicesJson = null;
        List<Device> devices = new ArrayList<Device>();
        ObjectMapper mapper = new ObjectMapper();
        try {

            params.put("Content-Type", "application/json");
            String encoding = new String(Base64.encodeBase64((userName + ":" + password).getBytes()));
            params.put("Authorization", "Basic " + encoding);
            String encodedURL = Utils.buildURI(scheme, server, devicesPath);

            HttpResponse httpResponse = Utils.executeGetRequest(encodedURL, params);
            if (httpResponse == null)
                throw new NullPointerException();
            int ResponseCode = httpResponse.getStatusLine().getStatusCode();
            if (ResponseCode == 200) {
                responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    String content = EntityUtils.toString(responseEntity);
                    org.json.simple.JSONObject result = (org.json.simple.JSONObject) (new JSONParser()).parse(content);
                    devicesJson = result.get("devices").toString();
                }
            } else {
                throw new Exception("Error occurred trying to retrieve devices");

            }
        } catch (Exception ex) {

            throw new Exception("Error occurred trying to retrieve devices : ", ex);

        }
        if (!Utils.isEmptyOrNull(devicesJson))
            devices = mapper.readValue(
                    devicesJson,
                    mapper.getTypeFactory().constructParametricType(List.class, Device.class));
        return devices;
    }

    public Device deviceDetails(String deviceId) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        HttpEntity responseEntity = null;
        String devicesJson = null;
        Device device = null;
        try {
            params.put("Content-Type", "application/json");
            String encoding = new String(Base64.encodeBase64((userName + ":" + password).getBytes()));
            params.put("Authorization", "Basic " + encoding);
            String encodedURL = Utils.buildURI(scheme, server, devicesPath + "/" + deviceId);

            HttpResponse httpResponse = Utils.executeGetRequest(encodedURL, params);
            if (httpResponse == null)
                throw new NullPointerException();
            int ResponseCode = httpResponse.getStatusLine().getStatusCode();
            if (ResponseCode == 200) {
                responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    String content = EntityUtils.toString(responseEntity);
                    org.json.simple.JSONObject result = (org.json.simple.JSONObject) (new JSONParser()).parse(content);
                    devicesJson = result.toString();
                }
            } else {
                throw new Exception("Error occurred trying to retrieve device detail");

            }
        } catch (Exception ex) {
            throw new Exception("Error occurred trying to retrieve device detail : ", ex);

        }
        if (!Utils.isEmptyOrNull(devicesJson))
            device = (Device) Utils.convertJsonToClassObject(devicesJson, Device.class);

        return device;
    }

    public boolean deleteDevice(String deviceId) throws Exception {
        return deleteDevice(deviceId,getAccessToken());
    }

    public boolean deleteDevices() throws Exception {
        try {
            List<Device> devices = enrolledDevicesList();
            String token = getAccessToken();
            for (Device device : devices) {
                deleteDevice(device.getDeviceId(), token);
            }
        } catch (Exception e) {
            throw new Exception("Error occurred trying to delete devices ", e);
        }

        return false;
    }

    protected String getAccessToken() throws Exception {
        String encodedURL = Utils.buildURI(this.scheme, this.server, this.accessTokenPath);
        return Utils.getAccessToken(this.userName, this.password, encodedURL, this.grantType, this.scope);
    }

    protected boolean deleteDevice(String deviceId, String accessToken) throws Exception {
        HashMap<String, String> params = new HashMap();
        params.put("Content-Type", "application/x-www-form-urlencoded");
        params.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse = null;

        String deleteDevicePath = devicesPath + "/" + deviceId;
        String encodedURL = Utils.buildURI(scheme, server, deleteDevicePath);
        httpResponse = Utils.executeDeleteRequest(encodedURL, params);

        if (httpResponse == null)
            throw new NullPointerException();

        int ResponseCode = httpResponse.getStatusLine().getStatusCode();
        return (ResponseCode == 200);
    }

}

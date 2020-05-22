package com.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class Utils {

    /**
     * Used to execute Post Request
     *
     * @param url
     * @param headerParams
     * @param body
     * @return
     */
    public static HttpResponse executePostRequest(String url, Map<String, String> headerParams, Object body) {

        HttpResponse httpResponse = null;
        HttpPost httpPost = new HttpPost(url);
        try {
            for (String key : headerParams.keySet())
                httpPost.addHeader(key, headerParams.get(key));
            if (body != null) {
                if (body instanceof String)
                    httpPost.setEntity(new StringEntity(""+body,"UTF-8"));
                else
                    httpPost.setEntity(new StringEntity(convertToJson(body),"UTF-8"));
            }


            CloseableHttpClient client = HttpClientBuilder.create().build();
            httpResponse = client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpResponse;
    }

    /**
     * Used to execute Get request
     *
     * @param url
     * @param headerParams
     * @return
     */
    public static HttpResponse executeGetRequest(String url, Map<String, String> headerParams) {
        HttpResponse httpResponse = null;

        try {
            HttpGet httpget = new HttpGet(url);
            for (String key : headerParams.keySet())
                httpget.addHeader(key, headerParams.get(key));
            HttpClient client = HttpClientBuilder.create().build();
            httpResponse = client.execute(httpget);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpResponse;
    }

 /**
     * Used to execute Delete request
     *
     * @param url
     * @param headerParams
     * @return
     */
    public static HttpResponse executeDeleteRequest(String url, Map<String, String> headerParams) {
        HttpResponse httpResponse = null;

        try {
            HttpDelete httpDelete = new HttpDelete(url);
            for (String key : headerParams.keySet())
                httpDelete.addHeader(key, headerParams.get(key));
            HttpClient client = HttpClientBuilder.create().build();
            httpResponse = client.execute(httpDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpResponse;
    }


    /**
     * Used to build URI
     *
     * @param schema
     * @param host
     * @param path
     * @return
     * @throws URISyntaxException
     */
    public static String buildURI(String schema, String host, String path) throws URISyntaxException {
        URI uri = (new URIBuilder()).setScheme(schema).setHost(host).setPath(path).build();
        return uri.toString();
    }

    /**
     * Used to convert an object to Json
     *
     * @param object
     * @return
     */
    public static String convertToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String utf8JsonString = objectMapper.writeValueAsString(object);
            byte[] bytes = utf8JsonString.getBytes("UTF8");
            json = new String(bytes,  "UTF-8");
            System.err.println("json : " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * isEmptyOrNull
     *
     * @param strValue
     * @return boolean
     * <p>
     * This method returns true if the given string is null or empty string,
     * otherwise returns false
     */
    public static boolean isEmptyOrNull(String strValue) {
        if (strValue == null || strValue.trim().length() == 0) {
            return true;
        }
        return false;
    }



    public static  String getAccessToken(String userName, String password, String encodedURL, String grantType, String scope) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        HttpEntity responseEntity = null;
        String token = null;
        try {
            params.put("Content-Type", "application/x-www-form-urlencoded");
            String encoding = new String(Base64.encodeBase64((userName + ":" + password).getBytes()));
            params.put("Authorization", "Basic " + encoding);
          //  String encodedURL = Utils.buildURI(this.scheme, this.server, this.accessTokenPath);

            HttpResponse httpResponse = executePostRequest(encodedURL, params, "grant_type=" + grantType + "&scope=" + scope);
            if (httpResponse == null)
                throw new NullPointerException();
            int ResponseCode = httpResponse.getStatusLine().getStatusCode();
            if (ResponseCode == 200) {
                responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    String content = EntityUtils.toString(responseEntity);
                    JSONObject result = (JSONObject) (new JSONParser()).parse(content);
                    token = result.get("access_token").toString();
                }
            } else {
                throw new Exception("Error occurred trying to retrieve token");

            }
        } catch (Exception ex) {

            throw new Exception("Error occurred trying to retrieve token : ", ex);

        }

        return token;
    }


    /**
     * This method used  to convert json to required Class
     *
     * @param json
     * @param classType
     * @return
     */
    public static Object convertJsonToClassObject(String json, Class classType)  throws Exception{
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, classType);
        } catch (Exception e) {
            throw new Exception("Error occurred trying to convert json Object : ", e);
        }
    }

}

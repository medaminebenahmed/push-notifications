package com.model;

import java.util.Map;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class Payload {
    private Map<String, String> content;

    public Payload(Map<String, String> content) {
        this.content = content;
    }

    public Payload() {
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}

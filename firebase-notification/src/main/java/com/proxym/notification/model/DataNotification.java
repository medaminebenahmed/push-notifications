package com.proxym.notification.model;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class DataNotification {

    private String title;
    private String image;
    private String text;
    private MetaData meta;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }


}

package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ©Proxym-Group : ESS POLE<br/>
 * Created by med-amine.ben-ahmed on 11/12/2017.
 */
public class Target {

    private List<String> deviceIds = new ArrayList<String>();
    private List<String> platforms = new ArrayList<String>();
    private List<String> tagNames = new ArrayList<String>();
    private List<String> userIds = new ArrayList<String>();

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public Target(List<String> deviceIds, List<String> platforms, List<String> tagNames, List<String> userIds) {
        this.deviceIds = deviceIds;
        this.platforms = platforms;
        this.tagNames = tagNames;
        this.userIds = userIds;

    }

    public Target() {
    }

    /**
     * Set of targets can be consumer Ids, devices, platforms, or tags. Only one of the targets can be set.<br/>
     * hence this method used to verify this condition
     * @return
     */
    public boolean verifyTarget() {
        boolean size1 = this.getDeviceIds().isEmpty();
        boolean size2 = this.getPlatforms().isEmpty();
        boolean size3 = this.getUserIds().isEmpty();
        boolean size4 = this.getTagNames().isEmpty();
        if (size1 && size2 && size3 && size4) {// no target have been specified
            return false;
        } else if (!size1 || !size2 || !size3 || !size4) {//if at least one target have been specified
            if (!size1) {//if deviceIds target have been specified
                if (!size2 || !size3 || !size4)// if at least another target have been specified
                    return false;
            } else if (!size2) {//if platform target have been specified

                if (!size3 || !size4)// if at least another target have been specified
                    return false;
            } else if (!size3)//if UserIds target have been specified
                return size4;

        }

        return true;
    }
}

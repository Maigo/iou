package com.maigo.iou.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

    private int groupId;
    private String name;
    private String description;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("group:[group_id: %d, name: %s]", groupId, name);
    }

}

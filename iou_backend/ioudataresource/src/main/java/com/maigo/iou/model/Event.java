package com.maigo.iou.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {

    private int eventId;
    private String name;
    private String description;
    private String date;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "event:[event_id: %d, name: %s, description: %s, date: %s]",
                eventId, name, description, date);
    }

}

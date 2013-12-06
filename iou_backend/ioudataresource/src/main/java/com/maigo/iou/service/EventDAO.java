package com.maigo.iou.service;

import java.util.List;

import com.maigo.iou.model.Event;

public interface EventDAO {

    // data read access
    public List<Event> findEventAll();

    public Event findEventByEventId(int eventId);

    public List<Event> findEventByUserId(int userId);

    public Event findEventByEventIdAndUserId(int eventId, int userId);

    // data write access
    public Event createEvent(Event event);

    public Event updateEventByEventId(Event event, int eventId);

    public void deleteEventByEventId(int eventId);

}

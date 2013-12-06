package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.Event;
import com.maigo.iou.service.EventDAO;
import com.maigo.iou.service.EventDAOImpl;

public class UserEventResource {

    // @Inject
    private EventDAO dao = new EventDAOImpl();

    private int userId;

    public UserEventResource(int userId) {
        this.userId = userId;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Event> findEventByUserId() {
        return dao.findEventByUserId(userId);
    }

    @GET
    @Path("{event_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Event findEventByEventIdAndUserId(@PathParam("event_id") int eventId) {
        return dao.findEventByEventIdAndUserId(eventId, userId);
    }

}
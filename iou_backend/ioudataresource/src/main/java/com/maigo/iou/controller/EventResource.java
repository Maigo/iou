package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.Event;
import com.maigo.iou.service.EventDAO;
import com.maigo.iou.service.EventDAOImpl;

@Path("/event")
public class EventResource {

    // @Inject
    private EventDAO dao = new EventDAOImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Event> findAll() {
        return dao.findEventAll();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Event createEvent(Event event) {
        return dao.createEvent(event);
    }

    @GET
    @Path("{event_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Event findEventByEventId(@PathParam("event_id") int eventId) {
        return dao.findEventByEventId(eventId);
    }

    @PUT
    @Path("{event_id: [0-9]+}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Event updateEventByEventId(Event event,
            @PathParam("event_id") int eventId) {
        return dao.updateEventByEventId(event, eventId);
    }

    @DELETE
    @Path("{event_id: [0-9]+}")
    public void deleteEvent(@PathParam("event_id") int eventId) {
        dao.deleteEventByEventId(eventId);
    }

    @Path("{event_id: [0-9]+}/user")
    public EventUserResource getEventUserResource(
            @PathParam("event_id") int eventId) {
        return new EventUserResource(eventId);
    }

}

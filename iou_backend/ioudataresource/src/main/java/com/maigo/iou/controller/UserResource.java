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

import com.maigo.iou.model.User;
import com.maigo.iou.service.UserDAO;
import com.maigo.iou.service.UserDAOImpl;

@Path("/user")
public class UserResource {

    // @Inject
    private UserDAO dao = new UserDAOImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> findUserAll() {
        return dao.findUserAll();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User createUser(User user) {
        return dao.createUser(user);
    }

    @GET
    @Path("{user_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User findById(@PathParam("user_id") int userId) {
        return dao.findUserByUserId(userId);
    }

    @PUT
    @Path("{user_id: [0-9]+}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User updateUserByUserId(User user, @PathParam("user_id") int userId) {
        return dao.updateUserByUserId(user, userId);
    }

    @DELETE
    @Path("{user_id: [0-9]+}")
    public void deleteUserByUserId(@PathParam("user_id") int userId) {
        dao.deleteUserByUserId(userId);
    }

    @Path("{user_id: [0-9]+}/group")
    public UserGroupResource getUserGroupResource(
            @PathParam("user_id") int userId) {
        return new UserGroupResource(userId);
    }

    @Path("{user_id: [0-9]+}/event")
    public UserEventResource getUserEventResource(
            @PathParam("user_id") int userId) {
        return new UserEventResource(userId);
    }

}

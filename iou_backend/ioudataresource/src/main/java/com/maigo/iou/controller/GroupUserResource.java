package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.User;
import com.maigo.iou.service.UserDAO;
import com.maigo.iou.service.UserDAOImpl;

public class GroupUserResource {

    // @Inject
    private UserDAO dao = new UserDAOImpl();

    private int groupId;

    public GroupUserResource(int groupId) {
        this.groupId = groupId;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> findAll() {
        return dao.findUserByGroupId(groupId);
    }

    @GET
    @Path("{user_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User findUserByUserIdAndGroupId(@PathParam("user_id") int userId) {
        return dao.findUserByUserIdAndGroupId(userId, groupId);
    }

}

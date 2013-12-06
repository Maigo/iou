package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.Group;
import com.maigo.iou.service.GroupDAO;
import com.maigo.iou.service.GroupDAOImpl;

public class UserGroupResource {

    // @Inject
    private GroupDAO dao = new GroupDAOImpl();

    private int userId;

    public UserGroupResource(int userId) {
        this.userId = userId;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Group> findGroupByUserId() {
        return dao.findGroupByUserId(userId);
    }

    @GET
    @Path("{group_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Group findGroupByGroupIdAndUserId(@PathParam("group_id") int groupId) {
        return dao.findGroupByGroupIdAndUserId(groupId, userId);
    }

}
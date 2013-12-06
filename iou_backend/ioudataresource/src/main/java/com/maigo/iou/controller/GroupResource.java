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

import com.maigo.iou.model.Group;
import com.maigo.iou.service.GroupDAO;
import com.maigo.iou.service.GroupDAOImpl;

@Path("/group")
public class GroupResource {

    // @Inject
    private GroupDAO dao = new GroupDAOImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Group> findAll() {
        return dao.findGroupAll();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Group createGroup(Group group) {
        return dao.createGroup(group);
    }

    @GET
    @Path("{group_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Group findById(@PathParam("group_id") int groupId) {
        return dao.findGroupByGroupId(groupId);
    }

    @PUT
    @Path("{group_id: [0-9]+}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Group updateGroup(Group group, @PathParam("group_id") int groupId) {
        return dao.updateGroupByGroupId(group, groupId);
    }

    @DELETE
    @Path("{group_id: [0-9]+}")
    public void deleteGroup(@PathParam("group_id") int groupId) {
        dao.deleteGroupByGroupId(groupId);
    }

    @Path("{group_id: [0-9]+}/user")
    public GroupUserResource getGroupUserResource(
            @PathParam("group_id") int groupId) {
        return new GroupUserResource(groupId);
    }

}

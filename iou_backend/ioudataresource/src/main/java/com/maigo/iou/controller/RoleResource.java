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

import com.maigo.iou.model.Role;
import com.maigo.iou.service.RoleDAO;
import com.maigo.iou.service.RoleDAOImpl;

@Path("/role")
public class RoleResource {

    // @Inject
    private RoleDAO dao = new RoleDAOImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Role> findAll() {
        return dao.findRoleAll();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Role createRole(Role role) {
        return dao.createRole(role);
    }

    @GET
    @Path("{role_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Role findRoleByRoleId(@PathParam("role_id") int roleId) {
        return dao.findRoleByRoleId(roleId);
    }

    @PUT
    @Path("{role_id: [0-9]+}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Role updateRoleByRoleId(Role role,
            @PathParam("role_id") int roleId) {
        return dao.updateRoleByRoleId(role, roleId);
    }

    @DELETE
    @Path("{role_id: [0-9]+}")
    public void deleteRole(@PathParam("role_id") int roleId) {
        dao.deleteRoleByRoleId(roleId);
    }

    @Path("{role_id: [0-9]+}/account")
    public RoleAccountResource getRoleAccountResource(
            @PathParam("role_id") int roleId) {
        return new RoleAccountResource(roleId);
    }
    
}

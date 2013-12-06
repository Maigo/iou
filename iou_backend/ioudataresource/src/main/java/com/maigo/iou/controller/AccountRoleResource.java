package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.Role;
import com.maigo.iou.service.RoleDAO;
import com.maigo.iou.service.RoleDAOImpl;

public class AccountRoleResource {

    // @Inject
    private RoleDAO dao = new RoleDAOImpl();

    private int accountId;

    public AccountRoleResource(int accountId) {
        this.accountId = accountId;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Role> findAll() {
        return dao.findRoleByAccountId(accountId);
    }

    @GET
    @Path("{user_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Role findRoleByRoleIdAndAccountId(@PathParam("role_id") int roleId) {
        return dao.findRoleByRoleIdAndAccountId(roleId, accountId);
    }

}

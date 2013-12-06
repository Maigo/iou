package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.Account;
import com.maigo.iou.service.AccountDAO;
import com.maigo.iou.service.AccountDAOImpl;

public class RoleAccountResource {

    // @Inject
    private AccountDAO dao = new AccountDAOImpl();

    private int roleId;

    public RoleAccountResource(int roleId) {
        this.roleId = roleId;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Account> findAll() {
        return dao.findAccountByRoleId(roleId);
    }

    @GET
    @Path("{account_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Account findAccountByAccountIdAndRoleId(@PathParam("account_id") int accountId) {
        return dao.findAccountByAccountIdAndRoleId(accountId, roleId);
    }

}

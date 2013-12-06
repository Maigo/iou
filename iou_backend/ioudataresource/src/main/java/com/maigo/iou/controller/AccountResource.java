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

import com.maigo.iou.model.Account;
import com.maigo.iou.service.AccountDAO;
import com.maigo.iou.service.AccountDAOImpl;

@Path("/account")
public class AccountResource {

    // @Inject
    private AccountDAO dao = new AccountDAOImpl();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Account> findAll() {
        return dao.findAccountAll();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Account createAccount(Account account) {
        return dao.createAccount(account);
    }

    @GET
    @Path("{account_id: [0-9]+}")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Account findAccountByAccountId(@PathParam("account_id") int accountId) {
        return dao.findAccountByAccountId(accountId);
    }

    @PUT
    @Path("{account_id: [0-9]+}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Account updateAccountByAccountId(Account account,
            @PathParam("account_id") int accountId) {
        return dao.updateAccountByAccountId(account, accountId);
    }

    @DELETE
    @Path("{account_id: [0-9]+}")
    public void deleteAccountByAccountId(@PathParam("account_id") int accountId) {
        dao.deleteAccountByAccountId(accountId);
    }

    @Path("{account_id: [0-9]+}/role")
    public AccountRoleResource getAccountRoleResource(
            @PathParam("account_id") int accountId) {
        return new AccountRoleResource(accountId);
    }

}

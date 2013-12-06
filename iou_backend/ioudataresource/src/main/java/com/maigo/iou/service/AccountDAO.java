package com.maigo.iou.service;

import java.util.List;

import com.maigo.iou.model.Account;

public interface AccountDAO {

    // data read access
    public List<Account> findAccountAll();

    public Account findAccountByAccountId(int accountId);

    public List<Account> findAccountByRoleId(int roleId);

    public Account findAccountByAccountIdAndRoleId(int accountId, int roleId);

    // data write access
    public Account createAccount(Account account);

    public Account updateAccountByAccountId(Account account, int accountId);

    public void deleteAccountByAccountId(int accountId);

}

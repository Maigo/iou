package com.maigo.iou.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maigo.iou.model.Account;
import com.maigo.iou.utils.ConnectionHelper;

public class AccountDAOImpl implements AccountDAO {

    // query templates
    private static final String QTPL_SELECT_ALL = "SELECT a.account_id, a.auth_id, a.auth_password, a.enabled FROM t_account a;";
    private static final String QTPL_SELECT_BY_ACCOUNT_ID = "SELECT a.account_id, a.auth_id, a.auth_password, a.enabled FROM t_account a WHERE a.account_id = ?;";
    private static final String QTPL_SELECT_BY_ROLE_ID = "SELECT a.account_id, a.auth_id, a.auth_password FROM t_account_role ar LEFT JOIN t_account a ON (ar.account_id = a.account_id) WHERE ar.role_id = ?;";
    private static final String QTPL_SELECT_BY_ROLE_ID_AND_ACCOUNT_ID = "SELECT a.account_id, a.auth_id, a.auth_password FROM t_account_role ar LEFT JOIN t_account a ON (ar.account_id = a.account_id) WHERE ar.role_id = ? AND ar.account_id = ?;";

    private static final String QTPL_INSERT_ACCOUNT = "INSERT INTO t_account (auth_id, auth_password, enabled, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);";
    private static final String QTPL_UPDATE_ACCOUNT = "UPDATE t_account a SET a.auth_id = ?, a.auth_password = ? WHERE a.account_id = ?;";
    private static final String QTPL_DELETE_ACCOUNT = "DELETE a FROM t_account a WHERE a.account_id = ?;";

    @Override
    public List<Account> findAccountAll() {
        List<Account> list = new ArrayList<Account>();
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(processResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    @Override
    public Account findAccountByAccountId(int accountId) {
        Account account = null;
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_BY_ACCOUNT_ID);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return account;
    }

    @Override
    public List<Account> findAccountByRoleId(int roleId) {
        List<Account> list = new ArrayList<Account>();
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_BY_ROLE_ID);
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(processResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    @Override
    public Account findAccountByAccountIdAndRoleId(int accountId, int roleId) {
        Account account = null;
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c
                    .prepareStatement(QTPL_SELECT_BY_ROLE_ID_AND_ACCOUNT_ID);
            ps.setInt(1, roleId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return account;
    }

    @Override
    public Account createAccount(Account account) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_INSERT_ACCOUNT,
                    new String[] { "account_id" });
            ps.setString(1, account.getAuthId());
            ps.setString(2, account.getAuthPassword());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int accountId = rs.getInt(1);
                account.setAccountId(accountId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return account;
    }

    @Override
    public Account updateAccountByAccountId(Account account, int accountId) {
        assert account.getAccountId() == accountId;

        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_UPDATE_ACCOUNT);
            ps.setString(1, account.getAuthId());
            ps.setString(2, account.getAuthPassword());
            ps.setInt(3, account.getAccountId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return account;
    }

    @Override
    public void deleteAccountByAccountId(int accountId) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_DELETE_ACCOUNT);
            ps.setInt(1, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    private Account processResultSet(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setAuthId(rs.getString("auth_id"));
        account.setAuthPassword(rs.getString("auth_password"));
        return account;
    }

}

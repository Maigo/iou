package com.maigo.iou.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maigo.iou.model.Role;
import com.maigo.iou.utils.ConnectionHelper;

public class RoleDAOImpl implements RoleDAO {

    // query templates
    private static final String QTPL_SELECT_ALL = "SELECT r.role_id, r.name, r.description FROM t_role r;";
    private static final String QTPL_SELECT_BY_ROLE_ID = "SELECT r.role_id, r.name, r.description FROM t_role r WHERE r.role_id = ?;";
    private static final String QTPL_SELECT_BY_ACCOUNT_ID = "SELECT r.role_id, r.name, r.description FROM t_account_role ar LEFT JOIN t_role r ON (ar.role_id = r.role_id) WHERE ar.account_id = ?;";
    private static final String QTPL_SELECT_BY_ACCOUNT_ID_AND_ROLE_ID = "SELECT r.role_id, r.name, r.description FROM t_account_role ar LEFT JOIN t_role r ON (ar.role_id = r.role_id) WHERE ar.account_id = ? AND ar.role_id = ?;";

    private static final String QTPL_INSERT_ROLE = "INSERT INTO t_role (name, description, modify_date, create_date) VALUES (?, ?, NULL, NULL);";
    private static final String QTPL_UPDATE_ROLE = "UPDATE t_role r SET r.name = ?, r.description = ? WHERE r.role_id = ?;";
    private static final String QTPL_DELETE_ROLE = "DELETE r FROM t_role r WHERE r.role_id = ?;";

    @Override
    public List<Role> findRoleAll() {
        List<Role> list = new ArrayList<Role>();
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
    public Role findRoleByRoleId(int roleId) {
        Role role = null;
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_BY_ROLE_ID);
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return role;
    }

    @Override
    public List<Role> findRoleByAccountId(int accountId) {
        List<Role> list = new ArrayList<Role>();
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_BY_ACCOUNT_ID);
            ps.setInt(1, accountId);
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
    public Role findRoleByRoleIdAndAccountId(int roleId, int accountId) {
        Role role = null;
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c
                    .prepareStatement(QTPL_SELECT_BY_ACCOUNT_ID_AND_ROLE_ID);
            ps.setInt(1, accountId);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return role;
    }

    @Override
    public Role createRole(Role role) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_INSERT_ROLE,
                    new String[] { "role_id" });
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int roleId = rs.getInt(1);
                role.setRoleId(roleId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return role;
    }

    @Override
    public Role updateRoleByRoleId(Role role, int roleId) {
        assert role.getRoleId() == roleId;

        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_UPDATE_ROLE);
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            ps.setInt(3, role.getRoleId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return role;
    }

    @Override
    public void deleteRoleByRoleId(int roleId) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_DELETE_ROLE);
            ps.setInt(1, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    private Role processResultSet(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        role.setName(rs.getString("name"));
        role.setDescription(rs.getString("description"));
        return role;
    }

}

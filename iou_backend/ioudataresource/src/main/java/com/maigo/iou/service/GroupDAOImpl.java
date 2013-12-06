package com.maigo.iou.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maigo.iou.model.Group;
import com.maigo.iou.utils.ConnectionHelper;

public class GroupDAOImpl implements GroupDAO {

    // query templates
    private static final String QTPL_SELECT_ALL = "SELECT g.group_id, g.name, g.description FROM t_group g;";
    private static final String QTPL_SELECT_BY_GROUP_ID = "SELECT g.group_id, g.name, g.description FROM t_group g WHERE g.group_id = ?;";
    private static final String QTPL_SELECT_BY_USER_ID = "SELECT g.group_id, g.name, g.description FROM t_user_group ug LEFT JOIN t_group g ON (ug.group_id = g.group_id) WHERE ug.user_id = ?;";
    private static final String QTPL_SELECT_BY_USER_ID_AND_GROUP_ID = "SELECT g.group_id, g.name, g.description FROM t_user_group ug LEFT JOIN t_group g ON (ug.group_id = g.group_id) WHERE ug.user_id = ? AND ug.group_id = ?;";

    private static final String QTPL_INSERT_GROUP = "INSERT INTO t_group (name, description, modify_date, create_date) VALUES (?, ?, NULL, NULL);";
    private static final String QTPL_UPDATE_GROUP = "UPDATE t_group g SET g.name = ?, g.description = ? WHERE g.group_id = ?;";
    private static final String QTPL_DELETE_GROUP = "DELETE g FROM t_group g WHERE g.group_id = ?;";

    @Override
    public List<Group> findGroupAll() {
        List<Group> list = new ArrayList<Group>();
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
    public Group findGroupByGroupId(int groupId) {
        Group group = null;
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_BY_GROUP_ID);
            ps.setInt(1, groupId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                group = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return group;
    }

    @Override
    public List<Group> findGroupByUserId(int userId) {
        List<Group> list = new ArrayList<Group>();
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_SELECT_BY_USER_ID);
            ps.setInt(1, userId);
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
    public Group findGroupByGroupIdAndUserId(int groupId, int userId) {
        Group group = null;
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c
                    .prepareStatement(QTPL_SELECT_BY_USER_ID_AND_GROUP_ID);
            ps.setInt(1, userId);
            ps.setInt(2, groupId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                group = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return group;
    }

    @Override
    public Group createGroup(Group group) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_INSERT_GROUP,
                    new String[] { "group_id" });
            ps.setString(1, group.getName());
            ps.setString(2, group.getDescription());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int groupId = rs.getInt(1);
                group.setGroupId(groupId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return group;
    }

    @Override
    public Group updateGroupByGroupId(Group group, int groupId) {
        assert group.getGroupId() == groupId;

        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_UPDATE_GROUP);
            ps.setString(1, group.getName());
            ps.setString(2, group.getDescription());
            ps.setInt(2, group.getGroupId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return group;
    }

    @Override
    public void deleteGroupByGroupId(int groupId) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(QTPL_DELETE_GROUP);
            ps.setInt(1, groupId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    private Group processResultSet(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setGroupId(rs.getInt("group_id"));
        group.setName(rs.getString("name"));
        group.setDescription(rs.getString("description"));
        return group;
    }

}

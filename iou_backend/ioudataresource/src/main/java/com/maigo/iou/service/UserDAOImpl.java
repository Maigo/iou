package com.maigo.iou.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maigo.iou.model.User;
import com.maigo.iou.utils.ConnectionHelper;

public class UserDAOImpl implements UserDAO {

    // query templates
    private static final String QTPL_SELECT_ALL = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user u;";
    private static final String QTPL_SELECT_BY_USER_ID = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user u WHERE u.user_id = ?;";
    private static final String QTPL_SELECT_BY_GROUP_ID = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_group ug LEFT JOIN t_user u ON (ug.user_id = u.user_id) WHERE ug.group_id = ?;";
    private static final String QTPL_SELECT_BY_GROUP_ID_AND_USER_ID = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_group ug LEFT JOIN t_user u ON (ug.user_id = u.user_id) WHERE ug.group_id = ? AND ug.user_id = ?;";
    private static final String QTPL_SELECT_BY_EVENT_ID = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_event ue LEFT JOIN t_user u ON (ue.user_id = u.user_id) WHERE ue.event_id = ?;";
    private static final String QTPL_SELECT_BY_EVENT_ID_AND_USER_ID = "SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_event ue LEFT JOIN t_user u ON (ue.user_id = u.user_id) WHERE ue.event_id = ? AND ue.user_id = ?;";

    private static final String QTPL_INSERT_USER = "INSERT INTO t_user (first_name, last_name, email, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);";
    private static final String QTPL_UPDATE_USER = "UPDATE t_user u SET u.first_name = ?, u.last_name = ?, u.email = ? WHERE u.user_id = ?;";
    private static final String QTPL_DELETE_USER = "DELETE u FROM t_user u WHERE u.user_id = ?;";

    @Override
    public List<User> findUserAll() {
        List<User> list = new ArrayList<User>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(processResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return list;
    }

    @Override
    public User findUserByUserId(int userId) {
        User user = null;

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return user;
    }

    @Override
    public List<User> findUserByGroupId(int groupId) {
        List<User> list = new ArrayList<User>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_GROUP_ID);
            ps.setInt(1, groupId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(processResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return list;
    }

    @Override
    public User findUserByUserIdAndGroupId(int userId, int groupId) {
        User user = null;

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_GROUP_ID_AND_USER_ID);
            ps.setInt(1, groupId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return user;
    }

    @Override
    public List<User> findUserByEventId(int eventId) {
        List<User> list = new ArrayList<User>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_EVENT_ID);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(processResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return list;
    }

    @Override
    public User findUserByUserIdAndEventId(int userId, int eventId) {
        User user = null;

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_EVENT_ID_AND_USER_ID);
            ps.setInt(1, eventId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_INSERT_USER, new String[] { "user_id" });
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int userId = rs.getInt(1);
                user.setUserId(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return user;
    }

    @Override
    public User updateUserByUserId(User user, int userId) {
        assert user.getUserId() == userId;

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_UPDATE_USER);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return user;
    }

    @Override
    public void deleteUserByUserId(int userId) {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_DELETE_USER);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private User processResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        return user;
    }

}

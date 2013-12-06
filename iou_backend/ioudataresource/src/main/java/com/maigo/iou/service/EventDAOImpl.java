package com.maigo.iou.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maigo.iou.model.Event;
import com.maigo.iou.utils.ConnectionHelper;

public class EventDAOImpl implements EventDAO {

    // query templates
    private static final String QTPL_SELECT_ALL = "SELECT e.event_id, e.name, e.description, e.date FROM t_event e;";
    private static final String QTPL_SELECT_BY_EVENT_ID = "SELECT e.event_id, e.name, e.description, e.date FROM t_event e WHERE e.event_id = ?;";
    private static final String QTPL_SELECT_BY_USER_ID = "SELECT e.event_id, e.name, e.description, e.date FROM t_user_event ue LEFT JOIN t_event e ON (ue.event_id = e.event_id) WHERE ue.user_id = ?;";
    private static final String QTPL_SELECT_BY_USER_ID_AND_EVENT_ID = "SELECT e.event_id, e.name, e.description, e.date FROM t_user_event ue LEFT JOIN t_event e ON (ue.event_id = e.event_id) WHERE ue.user_id = ? AND ue.event_id = ?;";

    private static final String QTPL_INSERT_EVENT = "INSERT INTO t_event (name, description, date, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);";
    private static final String QTPL_UPDATE_EVENT = "UPDATE t_event e SET e.name = ?, e.description = ?, e.date = ? WHERE e.event_id = ?;";
    private static final String QTPL_DELETE_EVENT = "DELETE e FROM t_event e WHERE e.event_id = ?;";

    @Override
    public List<Event> findEventAll() {
        List<Event> list = new ArrayList<Event>();

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
    public Event findEventByEventId(int eventId) {
        Event event = null;

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_EVENT_ID);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();

            if (rs.next()) {
                event = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return event;
    }

    @Override
    public List<Event> findEventByUserId(int userId) {
        List<Event> list = new ArrayList<Event>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_USER_ID);
            ps.setInt(1, userId);
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
    public Event findEventByEventIdAndUserId(int eventId, int userId) {
        Event event = null;

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_BY_USER_ID_AND_EVENT_ID);
            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            rs = ps.executeQuery();

            if (rs.next()) {
                event = processResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return event;
    }

    @Override
    public Event createEvent(Event event) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_INSERT_EVENT,
                    new String[] { "event_id" });
            ps.setString(1, event.getName());
            ps.setString(2, event.getDescription());
            ps.setString(3, event.getDate());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int eventId = rs.getInt(1);
                event.setEventId(eventId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return event;
    }

    @Override
    public Event updateEventByEventId(Event event, int eventId) {
        assert event.getEventId() == eventId;

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_UPDATE_EVENT);
            ps.setString(1, event.getName());
            ps.setString(2, event.getDescription());
            ps.setString(3, event.getDate());
            ps.setInt(4, event.getEventId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return event;
    }

    @Override
    public void deleteEventByEventId(int eventId) {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_DELETE_EVENT);
            ps.setInt(1, eventId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private Event processResultSet(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setName(rs.getString("name"));
        event.setDescription(rs.getString("description"));
        event.setDate(rs.getString("date"));
        return event;
    }

}

package com.maigo.iou.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maigo.iou.model.meta.MetaNumeric;
import com.maigo.iou.model.meta.MetaObject;
import com.maigo.iou.model.meta.MetaString;
import com.maigo.iou.utils.ConnectionHelper;

public class MetaDAOImpl implements MetaDAO {

    // query templates
    private static final String QTPL_SELECT_GROUP_META = "SELECT m.column_name, m.is_nullable, m.data_type, m.character_maximum_length, m.character_octet_length, m.numeric_precision, m.numeric_scale, m.character_set_name, m.collation_name FROM information_schema.columns m WHERE m.table_name = 't_group';";

    @Override
    public List<MetaObject> getGroupMetaData() {
        List<MetaObject> list = new ArrayList<MetaObject>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionHelper.getConnection();
            ps = cn.prepareStatement(QTPL_SELECT_GROUP_META);
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

    private MetaObject processResultSet(ResultSet rs) throws SQLException {
        MetaObject meta = null;

        String dataType = rs.getString("data_type");
        switch (dataType) {
        case "int":
            MetaNumeric metaNumeric = new MetaNumeric();
            metaNumeric.setNumericPrecision(rs.getInt("numeric_precision"));
            metaNumeric.setNumericScale(rs.getInt("numeric_scale"));
            meta = metaNumeric;
            break;
        case "varchar":
            MetaString metaString = new MetaString();
            metaString.setCharacterMaximumLength(rs
                    .getInt("character_maximum_length"));
            metaString.setCharacterSetName(rs.getString("character_set_name"));
            metaString.setCollationName(rs.getString("collation_name"));
            meta = metaString;
            break;
        default:
            System.out.println("DEFAULT!" + dataType);
            meta = new MetaObject();
        }

        meta.setName(rs.getString("column_name"));
        meta.setDataType(rs.getString("data_type"));
        meta.setNullable(rs.getBoolean("is_nullable"));

        return meta;
    }

}

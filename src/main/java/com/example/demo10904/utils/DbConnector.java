package com.example.demo10904.utils;

import com.example.demo10904.model.Db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 19750
 * @version 1.0
 */
public class DbConnector {
    public Connection connect(Db db) throws Exception {
        String url = db.getUrl();
        String username = db.getUsername();
        String password = db.getPassword();
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public List<String> getTableNames(Db db) throws Exception {
        Connection conn = connect(db);
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        List<String> tables = new ArrayList<>();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        rs.close();
        conn.close();
        return tables;
    }

    public Map<String, List<String>> getColumnNames(Db db) throws Exception {
        Connection conn = connect(db);
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(null, null, "%", "%");
        Map<String, List<String>> columns = new HashMap<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            String columnName = rs.getString("COLUMN_NAME");
            columns.computeIfAbsent(tableName, k -> new ArrayList<>()).add(columnName);
        }
        rs.close();
        conn.close();
        return columns;
    }
}

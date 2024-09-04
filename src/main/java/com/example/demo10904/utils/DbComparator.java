package com.example.demo10904.utils;

import com.example.demo10904.model.ColumnDiff;
import com.example.demo10904.model.CompareResult;
import com.example.demo10904.model.Db;
import com.example.demo10904.model.TableDiff;

import java.util.*;

/**
 * @author 19750
 * @version 1.0
 */
public class DbComparator {
    private DbConnector dbConnector = new DbConnector();
    public CompareResult compare(Db db1, Db db2) {
        try {
            List<String> tables1 = dbConnector.getTableNames(db1);
            List<String> tables2 = dbConnector.getTableNames(db2);

            List<TableDiff> tableDiffs = new ArrayList<>();
            Set<String> commonTables = new HashSet<>(tables1);
            commonTables.retainAll(tables2);

            for (String table : tables1) {
                if (!commonTables.contains(table)) {
                    tableDiffs.add(new TableDiff(table, 3)); // 表在db1中有而在db2中没有
                }
            }

            for (String table : tables2) {
                if (!commonTables.contains(table)) {
                    tableDiffs.add(new TableDiff(table, 4)); // 表在db2中有而在db1中没有
                }
            }

            // 对于共同拥有的表，检查列的差异
            Map<String, List<String>> columns1 = dbConnector.getColumnNames(db1);
            Map<String, List<String>> columns2 = dbConnector.getColumnNames(db2);

            List<ColumnDiff> columnDiffs = new ArrayList<>();
            for (String table : commonTables) {
                List<String> cols1 = columns1.get(table);
                List<String> cols2 = columns2.get(table);

                Set<String> commonCols = new HashSet<>(cols1);
                commonCols.retainAll(cols2);

                for (String col : cols1) {
                    if (!commonCols.contains(col)) {
                        columnDiffs.add(new ColumnDiff(table, col, 3)); // 字段在db1中有而在db2中没有
                    }
                }

                for (String col : cols2) {
                    if (!commonCols.contains(col)) {
                        columnDiffs.add(new ColumnDiff(table, col, 4)); // 字段在db2中有而在db1中没有
                    }
                }

                // 检查列属性差异
                for (String col : commonCols) {
                    // 这里需要具体的逻辑来比较列的属性
                    // 如类型、长度等
                    // 如果发现差异，则设置diff为2
                }
            }

            return new CompareResult(columnDiffs, tableDiffs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

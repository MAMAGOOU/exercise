package com.example.demo10904.model;

import lombok.Data;

/**
 * @author 19750
 * @version 1.0
 */
@Data
public class ColumnDiff {
    private String table;
    private String name;
    // 1=字段都有并且完全相同, 2=字段都有但是存在不同, 3=db1有 && db2没有, 4=db2有 && db1没有
    // 字段不同指：字段类型不同、字段长度不同、字段是否为空不同、字段默认值不同、
    // 字段是否为主键不同、字段是否自增不同
    private int diff;

    public ColumnDiff(String table, String name, int diff) {
        this.table = table;
        this.name = name;
        this.diff = diff;
    }
}

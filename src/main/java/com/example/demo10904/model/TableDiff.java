package com.example.demo10904.model;

import lombok.Data;

/**
 * @author 19750
 * @version 1.0
 */
@Data
public class TableDiff {
    private String name;
    // 1=表都有并且完全相同, 2=表都有但是存在不同, 3=db1有 && db2没有, 4=db2有 && db1没有
    private int diff;

    public TableDiff(String name, int diff) {
        this.name = name;
        this.diff = diff;
    }
}

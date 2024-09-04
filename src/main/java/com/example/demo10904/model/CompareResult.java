package com.example.demo10904.model;

import lombok.Data;

import java.util.List;

/**
 * @author 19750
 * @version 1.0
 */
@Data
public class CompareResult {
    private List<ColumnDiff> columnDiffs;
    private List<TableDiff> tableDiffs;

    public CompareResult(List<ColumnDiff> columnDiffs, List<TableDiff> tableDiffs) {
        this.columnDiffs = columnDiffs;
        this.tableDiffs = tableDiffs;
    }
}

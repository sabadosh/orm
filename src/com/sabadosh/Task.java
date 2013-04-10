package com.sabadosh;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tasks")
public class Task {

    public static final String NAME_FIELD_NAME = "task_name";
    public static final String PRIORITY_FIELD_NAME = "priority";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = PRIORITY_FIELD_NAME)
    private String priority;

    Task() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, String priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Task) other).name);
    }
}
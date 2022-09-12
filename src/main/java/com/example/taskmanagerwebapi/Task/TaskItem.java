package com.example.taskmanagerwebapi.Task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskItem {
    private String id;
    private String name;
    private boolean isCompleted;

    public TaskItem(String id, String name, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

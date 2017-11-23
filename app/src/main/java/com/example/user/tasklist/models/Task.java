package com.example.user.tasklist.models;


public class Task {

    private int id;
    private String name;
    private String description;
    private boolean completed;
    private boolean priority;


    public Task() {
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = false;
        this.priority = true;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public boolean getPriority() {
        return this.priority;
    }

    public int getPriorityInt() {
        if (this.priority) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getCompletedInt() {
        if (this.completed) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setPriority(boolean bool) {
        this.priority = bool;
    }

    public void setCompleted(boolean bool) {
        this.completed = bool;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

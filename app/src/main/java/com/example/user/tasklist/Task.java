package com.example.user.tasklist;

/**
 * Created by user on 19/11/2017.
 */

public class Task {

    private int id;
    private String name;
    private String description;
    private boolean completed;


    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = false;
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


}

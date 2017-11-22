package com.example.user.tasklist.Models;

/**
 * Created by user on 19/11/2017.
 */

public class Task {

    private int id;
    private String name;
    private String description;
    private int completed;


    public Task() {
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = 0;
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

    public int getCompleted() {
        return this.completed;
    }

    public void setCompleted(int bool) {
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

    public boolean getCompetedBoolean() {
        if (this.completed == 1) {
            return true;
        }
        else {
            return false;
        }
    }


}

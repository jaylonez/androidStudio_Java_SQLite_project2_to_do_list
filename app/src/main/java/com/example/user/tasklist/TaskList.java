package com.example.user.tasklist;

import java.util.ArrayList;

/**
 * Created by user on 19/11/2017.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();

        tasks.add(new Task(1, "Dishes", "gotta do"));
        tasks.add(new Task(2, "Washing", "less important"));
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}

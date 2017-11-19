package com.example.user.tasklist;

import java.util.ArrayList;

/**
 * Created by user on 19/11/2017.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();

        Task task1 = new Task(1, "Dishes", "gotta do");
        task1.setCompleted(true);

        tasks.add(task1);
        tasks.add(new Task(2, "Washing", "less important \nbut still \nand stuff"));

    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}

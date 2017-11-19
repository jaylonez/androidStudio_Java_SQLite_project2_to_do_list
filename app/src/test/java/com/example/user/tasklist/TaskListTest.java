package com.example.user.tasklist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 19/11/2017.
 */

public class TaskListTest {

    @Test
    public void canGetTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(2, taskList.getTasks().size());
    }
}

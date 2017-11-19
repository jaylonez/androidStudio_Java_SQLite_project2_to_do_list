package com.example.user.tasklist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 19/11/2017.
 */

public class TaskTest {

    Task task;

    @Before
    public void before() {
        task = new Task(1, "Dishes", "gotta go fast!");
    }

    @Test
    public void canGetId() {
        assertEquals(1, task.getId());
    }

    @Test
    public void canGetName() {
        assertEquals("Dishes", task.getName());
    }

    @Test
    public void canGetDescription() {
        assertEquals("gotta go fast!", task.getDescription());
    }
}

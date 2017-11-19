package com.example.user.tasklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskList taskList = new TaskList();

        ArrayList<Task> tasks = taskList.getTasks();

        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list);

        listView.setAdapter(taskAdapter);
    }

    public void getTask(View listItemSelected) {

        Task selectedTask = (Task) listItemSelected.getTag();

        Toast.makeText(this, selectedTask.getName(), Toast.LENGTH_SHORT).show();

    }

//    public void onCompletedClicked(View checkBoxSelected) {
//
//        CheckBox completed = (CheckBox) checkBoxSelected.getTag();
//
//        if (completed.isChecked()) {
//            Toast.makeText(this, completed.getName(), Toast.LENGTH_SHORT).show();
//        };
//
//    }
}

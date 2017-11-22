package com.example.user.tasklist.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.user.tasklist.R;
import com.example.user.tasklist.Database.TaskRepo;
import com.example.user.tasklist.Models.Task;
import com.example.user.tasklist.TaskListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskRepo sQLiteHelper = new TaskRepo(this);

        ArrayList<Task> tasks = sQLiteHelper.getAllTasks();

        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list);

        listView.setAdapter(taskAdapter);

    }



    public void getTask(View listItemSelected) {

        Task selectedTask = (Task) listItemSelected.getTag();

        Intent intent = new Intent(this, ViewTaskActivity.class);

        intent.putExtra("ID", selectedTask.getId());

        startActivity(intent);

    }

    public void onAddButtonClicked(View addButtonClicked) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public int checkedToBool(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }


    public void onCompletedClicked(View checkBoxSelected) {

        Task task = (Task) checkBoxSelected.getTag();
        CheckBox checkBox = (CheckBox) checkBoxSelected;


        int bool = checkedToBool(checkBox);


        TaskRepo sQLiteHelper = new TaskRepo(this);
        sQLiteHelper.getTaskByID(task.getId());

        sQLiteHelper.setTaskCompleted(task, bool);

    }

    public void onDeleteTask(View deleteButton) {


        TaskRepo sQLiteHelper = new TaskRepo(this);

        Task task = (Task) deleteButton.getTag();

        sQLiteHelper.deleteTask(task);

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

}
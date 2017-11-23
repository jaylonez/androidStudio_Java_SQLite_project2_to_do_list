package com.example.user.tasklist.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.user.tasklist.Database.TaskRepo;
import com.example.user.tasklist.Models.Task;
import com.example.user.tasklist.R;
import com.example.user.tasklist.TaskListAdapter;

import java.util.ArrayList;

public class PrioritiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_priorities);

        TaskRepo sQLiteHelper = new TaskRepo(this);

        ArrayList<Task> tasks = sQLiteHelper.getAllPriorityTasks();

        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list_starred);

        listView.setAdapter(taskAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.all_tasks) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.themes) {
            Intent intent = new Intent(this, ThemesActivity.class);
            startActivity(intent);
        }
        return true;
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

    public int checkedToInt(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }


    public void onCompletedClicked(View checkBoxSelected) {

        Task task = (Task) checkBoxSelected.getTag();
        CheckBox checkBox = (CheckBox) checkBoxSelected;


        int bool = checkedToInt(checkBox);


        TaskRepo sQLiteHelper = new TaskRepo(this);

        sQLiteHelper.setTaskBoolean(task, "TASK_COMPLETED", bool);

    }



    public void onDeleteTask(View deleteButton) {


        TaskRepo sQLiteHelper = new TaskRepo(this);

        Task task = (Task) deleteButton.getTag();

        sQLiteHelper.deleteTask(task);

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

}

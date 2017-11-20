package com.example.user.tasklist;

import android.content.Intent;
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

        SQLiteHelper sQLiteHelper = new SQLiteHelper(this);

        ArrayList<Task> tasks = sQLiteHelper.getAllRecords();

        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list);

        listView.setAdapter(taskAdapter);


    }

    public void getTask(View listItemSelected) {

        Task selectedTask = (Task) listItemSelected.getTag();

        Toast.makeText(this, selectedTask.getName(), Toast.LENGTH_SHORT).show();

    }

    public void onAddButtonClicked(View addButtonClicked) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

//    public void onCompletedClicked(View checkBoxSelected) {
//
//        Task completed = (Task) checkBoxSelected.getTag();
//        CheckBox bool = (CheckBox) checkBoxSelected;
//
//        String bool2;
//
//        if (completed.getCompleted()) {
//            bool2 = "true";
//        } else {
//            bool2 = "false";
//        }
//
//    }

}

package com.example.user.tasklist.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.user.tasklist.R;
import com.example.user.tasklist.Database.TaskRepo;
import com.example.user.tasklist.Models.Task;

/**
 * Created by user on 20/11/2017.
 */

public class AddTaskActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

    }

    public void onAddTask(View view) {
        EditText new_task_name = (EditText) findViewById(R.id.new_task_name);
        EditText new_task_description = (EditText) findViewById(R.id.new_task_description);
        TaskRepo sQLiteHelper = new TaskRepo(this);

        Task task = new Task();
        task.setName(new_task_name.getText().toString());
        task.setDescription(new_task_description.getText().toString());
        task.setCompleted(false);
        sQLiteHelper.insertTask(task);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

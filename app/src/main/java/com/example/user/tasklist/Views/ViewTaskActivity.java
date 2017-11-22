package com.example.user.tasklist.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.user.tasklist.R;
import com.example.user.tasklist.Database.TaskRepo;
import com.example.user.tasklist.Models.Task;

public class ViewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        EditText name = findViewById(R.id.view_name);
        EditText description = findViewById(R.id.view_description);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        int viewed_id = extras.getInt("ID");

        TaskRepo sQLiteHelper = new TaskRepo(this);

        Task task = sQLiteHelper.getTaskByID(viewed_id);

        name.setText(task.getName());
        description.setText(task.getDescription());

    }


    public void onDeleteTask(View deleteButton) {

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        int viewed_id = extras.getInt("ID");

        TaskRepo sQLiteHelper = new TaskRepo(this);

        Task task = sQLiteHelper.getTaskByID(viewed_id);

        sQLiteHelper.deleteTask(task);

        Intent intent2 = new Intent(this, MainActivity.class);

        startActivity(intent2);


    }
}

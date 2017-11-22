package com.example.user.tasklist.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.user.tasklist.R;
import com.example.user.tasklist.Database.TaskRepo;
import com.example.user.tasklist.Models.Task;

import java.util.Timer;
import java.util.TimerTask;

public class ViewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        final EditText name = findViewById(R.id.view_name);
        final EditText description = findViewById(R.id.view_description);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        int viewed_id = extras.getInt("ID");

        final TaskRepo sQLiteHelper = new TaskRepo(this);

        final Task task = sQLiteHelper.getTaskByID(viewed_id);

        name.setText(task.getName());
        description.setText(task.getDescription());

        name.addTextChangedListener(
                new TextWatcher() {
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    private Timer timer = new Timer();
                    private final long DELAY = 500; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        task.setName(name.getText().toString());
                                        sQLiteHelper.setTaskString(task, "TASK_NAME", task.getName());
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );

        description.addTextChangedListener(
                new TextWatcher() {
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    private Timer timer = new Timer();
                    private final long DELAY = 500; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        task.setDescription(description.getText().toString());
                                        sQLiteHelper.setTaskString(task, "TASK_DESCRIPTION", task.getDescription());
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );

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

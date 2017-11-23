package com.example.user.tasklist.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.user.tasklist.R;
import com.example.user.tasklist.database.TaskRepo;
import com.example.user.tasklist.models.Task;

import java.util.Timer;
import java.util.TimerTask;

public class ViewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_view_task);

        final EditText name = findViewById(R.id.view_name);
        final EditText description = findViewById(R.id.view_description);
        final CheckBox completed = findViewById(R.id.completed_checkbox);
        final CheckBox priority = findViewById(R.id.priority_checkbox);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        if (extras != null) {

            int viewed_id = extras.getInt("ID");

            final TaskRepo sQLiteHelper = new TaskRepo(this);

            final Task task = sQLiteHelper.getTaskByID(viewed_id);

            name.setText(task.getName());
            description.setText(task.getDescription());
            completed.setChecked(task.getCompleted());
            priority.setChecked(task.getPriority());

            final Context context = getApplicationContext();

            completed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();

                    Bundle extras = intent.getExtras();

                    if (extras != null) {

                        int viewed_id = extras.getInt("ID");


                        TaskRepo sQLiteHelper = new TaskRepo(context);

                        Task task = sQLiteHelper.getTaskByID(viewed_id);

                        CheckBox checkBox = (CheckBox) v;

                        int bool = checkedToInt(checkBox);


                        sQLiteHelper.setTaskBoolean(task, "TASK_COMPLETED", bool);
                    }
                }
            });

            priority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();

                    Bundle extras = intent.getExtras();

                    if (extras != null) {

                        int viewed_id = extras.getInt("ID");

                        TaskRepo sQLiteHelper = new TaskRepo(context);

                        Task task = sQLiteHelper.getTaskByID(viewed_id);


                        CheckBox ratingBar = (CheckBox) v;


                        int bool = checkedToInt(ratingBar);


                        sQLiteHelper.setTaskBoolean(task, "TASK_PRIORITY", bool);
                    }
                }
            });

            name.addTextChangedListener(
                    new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

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
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

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
    }


    public void onDeleteTask(View deleteButton) {

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        if (extras != null) {

            int viewed_id = extras.getInt("ID");

            TaskRepo sQLiteHelper = new TaskRepo(this);

            Task task = sQLiteHelper.getTaskByID(viewed_id);

            sQLiteHelper.deleteTask(task);

            Intent intent2 = new Intent(this, MainActivity.class);

            startActivity(intent2);
        }

    }

    public int checkedToInt(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }

}

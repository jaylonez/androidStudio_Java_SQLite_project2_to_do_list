package com.example.user.tasklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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

        SQLiteHelper sQLiteHelper = new SQLiteHelper(this);

        Task task = sQLiteHelper.getTaskByID(viewed_id);

        name.setText(task.getName());
        description.setText(task.getDescription());

    }
}

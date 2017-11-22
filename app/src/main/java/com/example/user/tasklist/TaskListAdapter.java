package com.example.user.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tasklist.Models.Task;
import com.example.user.tasklist.Views.MainActivity;

import java.util.ArrayList;

/**
 * Created by user on 19/11/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.tasks_item, parent, false);
        }

        Task currentTaskItem = getItem(position);

        final TextView name = listItemView.findViewById(R.id.name);
        name.setText(currentTaskItem.getName());

        final TextView description = listItemView.findViewById(R.id.description);
        description.setText(currentTaskItem.getDescription());

        CheckBox completed = listItemView.findViewById(R.id.completed);
        completed.setChecked(currentTaskItem.getCompleted());

        completed.setTag(currentTaskItem);

        final Button delete_task = (Button) listItemView.findViewById(R.id.delete_task);

        RelativeLayout constraint = listItemView.findViewById(R.id.constraint);

        constraint.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                
                name.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
                delete_task.setVisibility(View.VISIBLE);
                return true;
            }
        });

        delete_task.setTag(currentTaskItem);

        constraint.setTag(currentTaskItem);

        listItemView.setTag(currentTaskItem);

        return listItemView;

    }
}

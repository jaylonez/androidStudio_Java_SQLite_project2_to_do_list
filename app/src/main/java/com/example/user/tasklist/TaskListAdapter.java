package com.example.user.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.tasklist.Models.Task;

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

        TextView name = listItemView.findViewById(R.id.name);
        name.setText(currentTaskItem.getName());

        TextView description = listItemView.findViewById(R.id.description);
        description.setText(currentTaskItem.getDescription());

        CheckBox completed = listItemView.findViewById(R.id.completed);
        completed.setChecked(currentTaskItem.getCompleted());

        completed.setTag(currentTaskItem);

        RelativeLayout constraint = listItemView.findViewById(R.id.constraint);

        constraint.setTag(currentTaskItem);

        listItemView.setTag(currentTaskItem);

        return listItemView;

    }
}

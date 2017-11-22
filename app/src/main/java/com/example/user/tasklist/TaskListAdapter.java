package com.example.user.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tasklist.Database.TaskRepo;
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

        final TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(currentTaskItem.getName());

        final TextView description = (TextView) listItemView.findViewById(R.id.description);
        description.setText(currentTaskItem.getDescription());

        final CheckBox completed = (CheckBox) listItemView.findViewById(R.id.completed);
        completed.setChecked(currentTaskItem.getCompleted());

        final CheckBox priority_bar = (CheckBox) listItemView.findViewById(R.id.priority_bar);
        priority_bar.setChecked(currentTaskItem.getPriority());


        final Button delete_task = (Button) listItemView.findViewById(R.id.delete_task);



        final RelativeLayout constraint = listItemView.findViewById(R.id.constraint);
        final RelativeLayout constraint2 = listItemView.findViewById(R.id.constraint2);

        constraint.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                name.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
                delete_task.setVisibility(View.VISIBLE);
                priority_bar.setVisibility(View.VISIBLE);

                priority_bar.setOnClickListener(new View.OnClickListener() {

                    public int checkedToInt(CheckBox checkBox) {
                        if (checkBox.isChecked()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }

                    @Override
                    public void onClick(View PriorityBoxSelected) {

                        Task task = (Task) PriorityBoxSelected.getTag();
                        CheckBox ratingBar = (CheckBox) PriorityBoxSelected;


                        int bool = checkedToInt(ratingBar);

                         TaskRepo sQLiteHelper = new TaskRepo(getContext());

                        sQLiteHelper.setTaskBoolean(task, "TASK_PRIORITY", bool);

                    }
                });
                constraint2.setVisibility(View.VISIBLE);
                return true;
            }
        });

        constraint2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                name.setVisibility(View.VISIBLE);
                description.setVisibility(View.VISIBLE);
                delete_task.setVisibility(View.GONE);
                priority_bar.setVisibility(View.GONE);
                constraint2.setVisibility(View.GONE);
                return true;
            }
        });



        priority_bar.setTag(currentTaskItem);

        completed.setTag(currentTaskItem);

        delete_task.setTag(currentTaskItem);

        constraint.setTag(currentTaskItem);

        listItemView.setTag(currentTaskItem);

        return listItemView;

    }
}

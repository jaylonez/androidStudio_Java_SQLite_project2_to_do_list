package com.example.user.tasklist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.tasklist.R;
import com.example.user.tasklist.database.TaskRepo;
import com.example.user.tasklist.models.Task;

import java.util.ArrayList;

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    @NonNull
    public View getView(final int position, View listItemView, @NonNull ViewGroup parent) {

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.tasks_item, parent, false);
        }

        Task currentTaskItem = getItem(position);

        if (currentTaskItem != null) {

            final TextView name = listItemView.findViewById(R.id.name);
            name.setText(currentTaskItem.getName());

            final TextView description = listItemView.findViewById(R.id.description);
            description.setText(currentTaskItem.getDescription());

            final CheckBox completed = listItemView.findViewById(R.id.completed);
            completed.setChecked(currentTaskItem.getCompleted());

            final CheckBox priority_bar = listItemView.findViewById(R.id.priority_bar);
            priority_bar.setChecked(currentTaskItem.getPriority());


            final Button delete_task = listItemView.findViewById(R.id.delete_task);


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

                        int checkedToInt(CheckBox checkBox) {
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
        }

        return listItemView;

    }
}

package com.task.square.black.taskmanagment.adapter;

import com.task.square.black.taskmanagment.DB.Task;

public interface AdapterClickListener {
    void onItemClick(Task taskCLicked);
    void onStatusClicked(Task taskCLicked, int adapterPosition);
    void onTitleClicked(Task taskCLicked);
    void onLongClickPresed(Task taskClicked, int adapterPosition);

    void onDeleteTask(Task taskClicked, int adapterPosition);

    void updateDatabase(Task taskClicked, int adapterPosition);
}

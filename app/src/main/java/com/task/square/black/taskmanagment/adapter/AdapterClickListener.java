package com.task.square.black.taskmanagment.adapter;

import com.task.square.black.taskmanagment.DB.Task;

public interface AdapterClickListener {
    void onItemClick(Task taskCLicked);
    void onStatusClicked(Task taskCLicked);
    void onTitleClicked(Task taskCLicked);
    void onLongClickPresed(Task taskClicked);

    void onDeleteTask(Task taskClicked);

    void updateDatabase(Task taskClicked);
}

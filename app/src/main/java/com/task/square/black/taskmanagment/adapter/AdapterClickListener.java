package com.task.square.black.taskmanagment.adapter;

import com.task.square.black.taskmanagment.DB.Task;

public interface AdapterClickListener {
    void onItemClick(Task taskCLicked);
    void onLongClickPresed(Task taskClicked);

}

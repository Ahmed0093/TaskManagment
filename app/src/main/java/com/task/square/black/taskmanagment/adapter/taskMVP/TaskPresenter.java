package com.task.square.black.taskmanagment.adapter.taskMVP;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TaskRemoteFireBaseDataBase;
import com.task.square.black.taskmanagment.DB.localdb.TasksDataSource;

import java.util.List;

public class TaskPresenter implements TasksContract.Presenter {
    private final TasksContract.View mTasksView;
    private final TaskLocalDataSource taskLocalDataSource;
    private static volatile DatabaseReference INSTANCE;

    private boolean mFirstLoad = true;

    public TaskPresenter(TasksContract.View mTasksView, TaskLocalDataSource taskLocalDataSource) {
        this.mTasksView = mTasksView;
        this.taskLocalDataSource = taskLocalDataSource;
        mTasksView.setPresenter(this);
        INSTANCE = FirebaseDatabase.getInstance().getReference();


    }


    @Override
    public void loadTasks(boolean forceUpdate) {
    }

    @Override
    public void addNewTask() {
        //creating a task

    }

    @Override
    public void addTasktoDp(Task task) {
        taskLocalDataSource.saveTask(task);
        INSTANCE.child("users").child(String.valueOf(task.getId())).setValue(task); //TODO Complete FIRE BASE
        ShowTaskInView();
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {

    }

    @Override
    public void deleteTask(Task taskClicked) {
        taskLocalDataSource.deleteTask(String.valueOf(taskClicked.getId()));
        ShowTaskInView();
    }

    private void ShowTaskInView() {
        taskLocalDataSource.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.showTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showNoTasks();
            }
        });
    }

    @Override
    public void changeTaskStatus(Task taskCLicked) {
        if(taskCLicked.isIscompleted()) // Change Status
        {
            taskCLicked.setIscompleted(false);
        } else {
            taskCLicked.setIscompleted(true);

        }
        taskLocalDataSource.updateTask(taskCLicked);
        ShowTaskInView();
    }

    @Override
    public void updateAdapterUI(Task taskClicked) {
        if(taskClicked.isLongClickedPressed()) // Change Status
        {
            taskClicked.setLongClickedPressed(false);
        } else {
            taskClicked.setLongClickedPressed(true);

        }
        taskLocalDataSource.updateTask(taskClicked);
        ShowTaskInView();
    }
}

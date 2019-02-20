package com.task.square.black.taskmanagment.adapter.taskMVP;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TasksDataSource;

import java.util.ArrayList;
import java.util.List;

public class TaskPresenter implements TasksContract.Presenter {
    private final TasksContract.View mTasksView;
    private final TaskLocalDataSource taskLocalDataSource;
    private static volatile DatabaseReference INSTANCE;

    private boolean mFirstLoad = true;
    private List<Task> taskListtoBeReturned;

    public TaskPresenter(TasksContract.View mTasksView, TaskLocalDataSource taskLocalDataSource) {
        this.mTasksView = mTasksView;
        this.taskLocalDataSource = taskLocalDataSource;
        mTasksView.setPresenter(this);
        INSTANCE = FirebaseDatabase.getInstance().getReference();


    }


    @Override
    public void loadTasks() {
        ShowTaskInView();
    }

    @Override
    public void addNewTask() {
        //creating a task

    }

    @Override
    public void addTasktoDp(Task task) {
        taskLocalDataSource.saveTask(task);
//        INSTANCE.child("users").child(String.valueOf(task.getId())).setValue(task); //TODO Complete FIRE BASE
        ShowTaskInView();
    }

    private void notifyTaskisAdded(Task task) {
        taskLocalDataSource.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.notifyTaskInserted(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showNoTasks();
            }
        });
    }

    @Override
    public List<Task> getTasks() {

        return getTaskFromDP() ;
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {

    }

    @Override
    public void deleteTask(Task taskClicked, int adapterPosition) {
        taskLocalDataSource.deleteTask(String.valueOf(taskClicked.getId()));
        updateListWithDeletedItem(adapterPosition);
    }

    private void updateListWithDeletedItem(final int adapterPosition) {
        taskLocalDataSource.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.showTasksWhenItemDeletedWithPosition(tasks,adapterPosition);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showNoTasks();
            }
        });
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
    private List<Task> getTaskFromDP() {
        taskListtoBeReturned = new ArrayList<Task>();
        taskLocalDataSource.getTasks(new TasksDataSource.LoadTasksCallback() {

            @Override
            public void onTasksLoaded(List<Task> tasks) {
              taskListtoBeReturned= tasks;           }

            @Override
            public void onDataNotAvailable() {
                taskListtoBeReturned = new ArrayList<Task>();
            }
        });
    return taskListtoBeReturned;
    }

    @Override
    public void changeTaskStatus(Task taskCLicked, int adapterPosition) {
//        if(taskCLicked.isIscompleted()) // Change Status
//        {
//            taskCLicked.setIscompleted(false);
//        } else {
//            taskCLicked.setIscompleted(true);
//
//        }
//        taskLocalDataSource.updateTask(taskCLicked);
        updateListWithTaskitem(taskCLicked,adapterPosition);
    }

    @Override
    public void updateAdapterUI(Task taskClicked, int adapterPosition) {
        if(taskClicked.isLongClickedPressed()) // Change Status
        {
            taskClicked.setLongClickedPressed(false);
        } else {
            taskClicked.setLongClickedPressed(true);

        }
        taskLocalDataSource.updateTask(taskClicked);
        updateListWithTaskitem(taskClicked,adapterPosition);
    }

    @Override
    public void navigateToTaskView(Task taskCLicked) {
        mTasksView.OpenTaskViewDetailsWithCommentList(taskCLicked);
    }

    @Override
    public void updateTaskinDatabase(Task taskClicked, int adapterPosition) {
        taskLocalDataSource.updateTask(taskClicked);
        updateListWithTaskitem(taskClicked, adapterPosition);
    }

    private void updateListWithTaskitem(Task taskCLicked, final int adapterPosition) {
        taskLocalDataSource.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.updateTaskItem(tasks,adapterPosition);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showNoTasks();
            }
        });
    }

    @Override
    public void filterDoneTasksOnly() {
        taskLocalDataSource.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> result = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.isIscompleted()) {
                        result.add(task);
                    }
                }
                mTasksView.showFilteredDoneTasks(result);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showNoTasks();
            }
        });
    }
}

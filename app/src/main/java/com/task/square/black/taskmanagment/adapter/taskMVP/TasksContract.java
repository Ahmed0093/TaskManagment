package com.task.square.black.taskmanagment.adapter.taskMVP;

import android.support.annotation.NonNull;

import com.task.square.black.taskmanagment.DB.Task;

import java.util.List;

public interface TasksContract {
    interface View  {

//        void setLoadingIndicator(boolean active);
        void setPresenter(TasksContract.Presenter presenter);
        void showTasks(List<Task> tasks);

        void showAddTask();

        void showTaskDetailsUi(String taskId);

        void showTaskMarkedComplete();

        void showTaskMarkedActive();

        void showCompletedTasksCleared();

        void showLoadingTasksError();

        void showNoTasks();

        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();

        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showSuccessfullySavedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();
    }

    interface Presenter  {

//        void result(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate);

        void addNewTask();
        void addTasktoDp(Task task);


        void openTaskDetails(@NonNull Task requestedTask);

        void deleteTask(Task taskClicked);

        void changeTaskStatus(Task taskCLicked);

        void updateAdapterUI(Task taskClicked);
//
//        void completeTask(@NonNull Task completedTask);
//
//        void activateTask(@NonNull Task activeTask);
//
//        void clearCompletedTasks();
//
//        void setFiltering(TasksFilterType requestType);
//
//        TasksFilterType getFiltering();
    }
}

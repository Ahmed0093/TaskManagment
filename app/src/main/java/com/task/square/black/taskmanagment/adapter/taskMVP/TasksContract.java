package com.task.square.black.taskmanagment.adapter.taskMVP;

import androidx.annotation.NonNull;

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

        void OpenTaskViewDetailsWithCommentList(Task taskCLicked);

        void showTasksWhenItemDeletedWithPosition(List<Task> tasks, int adapterPosition);

        void notifyTaskInserted(List<Task> tasks);

        void updateTaskItem(List<Task> tasks, int adapterPosition);

        void showFilteredDoneTasks(List<Task> result);
    }

    interface Presenter  {

//        void result(int requestCode, int resultCode);

        void loadTasks();

        void addNewTask();
        void addTasktoDp(Task task);
        List<Task> getTasks();


        void openTaskDetails(@NonNull Task requestedTask);

        void deleteTask(Task taskClicked, int adapterPosition);

        void changeTaskStatus(Task taskCLicked, int adapterPosition);

        void updateAdapterUI(Task taskClicked, int adapterPosition);

        void navigateToTaskView(Task taskCLicked);

        void updateTaskinDatabase(Task taskClicked, int adapterPosition);

        void filterDoneTasksOnly();
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

package com.task.square.black.taskmanagment.adapter.taskMVP.taskDetailsMVP;

import com.task.square.black.taskmanagment.DB.Comment;
import com.task.square.black.taskmanagment.DB.Task;

import java.util.List;

public interface TaskDetailsContract {
    interface View  {

        void setPresenter(TaskDetailsContract.Presenter presenter);
        void showTaskWithComment(List<Task> tasks);


        void showTaskDetailsUi(String taskId);



        void showCompletedTasksCleared();



        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();


        void showComments(List<Comment> comments);

        void showNoComments();

        void updateUi();

        void backToTasksListAsTaskDeletedFromdetails();
    }

    interface Presenter  {

//        void result(int requestCode, int resultCode);

        void loadTaskWithComment();

        void deleteTask(Task taskClicked);

        void changeTaskStatus(Task taskCLicked);

        void updateAdapterUI(Task taskClicked);

        void navigateToTaskView(Task taskCLicked);

        void updateTaskinDatabase(Task taskClicked);


        void loadTaskWithMappingComments();

        void saveTask(int id, Comment comment);

        void updateDetailsActivityUi(int id);
    }
}

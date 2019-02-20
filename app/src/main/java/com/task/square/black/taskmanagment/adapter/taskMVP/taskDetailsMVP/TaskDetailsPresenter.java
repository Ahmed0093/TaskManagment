package com.task.square.black.taskmanagment.adapter.taskMVP.taskDetailsMVP;

import com.google.firebase.database.DatabaseReference;
import com.task.square.black.taskmanagment.DB.Comment;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.CommentDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;

import java.util.List;

public class TaskDetailsPresenter implements TaskDetailsContract.Presenter {
    private final TaskDetailsContract.View mTaskDetailView;
    private final TaskLocalDataSource taskLocalDataSource;
    private static volatile DatabaseReference INSTANCE;

    public TaskDetailsPresenter(TaskDetailsContract.View mTaskDetailView, TaskLocalDataSource taskLocalDataSource) {
        this.mTaskDetailView = mTaskDetailView;
        this.taskLocalDataSource = taskLocalDataSource;
        mTaskDetailView.setPresenter(this);
    }

    @Override
    public void loadTaskWithComment() {

    }

    @Override
    public void deleteTask(Task taskClicked) {
        taskLocalDataSource.deleteTask(String.valueOf(taskClicked.getId()));
        mTaskDetailView.backToTasksListAsTaskDeletedFromdetails();
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
    }

    @Override
    public void updateAdapterUI(Task taskClicked) {

    }

    @Override
    public void navigateToTaskView(Task taskCLicked) {

    }

    @Override
    public void updateTaskinDatabase(Task taskClicked) {
        taskLocalDataSource.updateTask(taskClicked);

    }

    @Override
    public void loadTaskWithMappingComments() {

    }

    @Override
    public void saveTask(int id, Comment comment) {
        taskLocalDataSource.saveComment(comment);
    }

    @Override
    public void updateDetailsActivityUi(int taskId) {
        taskLocalDataSource.getCommentByTaskId(String.valueOf(taskId), new CommentDataSource.GetCommentCallback() {
            @Override
            public void onCommentsLoaded(List<Comment> comments) {
                mTaskDetailView.showComments(comments);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    private void ShowCommentsInTaskView(String taskid) {
        taskLocalDataSource.getCommentByTaskId(taskid, new CommentDataSource.GetCommentCallback() {
            @Override
            public void onCommentsLoaded(List<Comment> comments) {
                mTaskDetailView.showComments(comments);
            }

            @Override
            public void onDataNotAvailable() {
                mTaskDetailView.showNoComments();

            }
        });
    }
    }

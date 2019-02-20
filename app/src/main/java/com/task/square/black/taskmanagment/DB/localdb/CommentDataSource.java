package com.task.square.black.taskmanagment.DB.localdb;

import android.support.annotation.NonNull;

import com.task.square.black.taskmanagment.DB.Comment;
import com.task.square.black.taskmanagment.DB.Task;

import java.util.List;

public interface CommentDataSource {
    interface LoadCommentsCallback {

        void onCommentsLoaded(List<Comment> commentsWithTaskId);

        void onDataNotAvailable();
    }

    interface GetCommentCallback {

        void onCommentsLoaded(List<Comment> comment);

        void onDataNotAvailable();
    }

    void getCommentsByTaskID(@NonNull LoadCommentsCallback callback);

    void getCommentByTaskId(@NonNull String taskId, @NonNull GetCommentCallback callback);

    void saveComment(@NonNull Comment comment);
//
    void updateComment(@NonNull Comment comment);
//
//    void completeTask(@NonNull String taskId);
//
//    void activateTask(@NonNull Task task);
//
//    void activateTask(@NonNull String taskId);
//
//    void clearCompletedTasks();
//
//    void refreshTasks();
//
//    void deleteAllTasks();
//
    void deleteCommentsForTaskId(@NonNull String taskId);
}

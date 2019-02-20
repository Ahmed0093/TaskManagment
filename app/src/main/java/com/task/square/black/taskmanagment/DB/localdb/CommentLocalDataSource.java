//package com.task.square.black.taskmanagment.DB.localdb;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//
//import com.task.square.black.taskmanagment.DB.Comment;
//import com.task.square.black.taskmanagment.DB.TaskDao;
//
//public class CommentLocalDataSource implements CommentDataSource {
//
//    private static volatile TaskLocalDataSource INSTANCE;
//
//    private TaskDao mTasksDao;
//
//    private Context mAppExecutors;
//
//    // Prevent direct instantiation.
//    private TaskLocalDataSource(@NonNull Context appExecutors,
//                                @NonNull TaskDao tasksDao) {
//        mAppExecutors = appExecutors;
//        mTasksDao = tasksDao;
//    }
//
//    public static TaskLocalDataSource getInstance(@NonNull Context appExecutors,
//                                                  @NonNull TaskDao tasksDao) {
//        if (INSTANCE == null) {
//            synchronized (TaskLocalDataSource.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new TaskLocalDataSource(appExecutors, tasksDao);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//
//
//    @Override
//    public void getCommentsByTaskID(@NonNull LoadCommentsCallback callback) {
//
//    }
//
//    @Override
//    public void getCommentByTaskId(@NonNull String taskId, @NonNull GetCommentCallback callback) {
//
//    }
//
//    @Override
//    public void saveComment(@NonNull Comment comment) {
//
//    }
//
//    @Override
//    public void updateComment(@NonNull Comment comment) {
//
//    }
//
//    @Override
//    public void deleteCommentsForTaskId(@NonNull String taskId) {
//
//    }
//}

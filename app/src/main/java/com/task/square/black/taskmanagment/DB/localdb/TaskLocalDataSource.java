package com.task.square.black.taskmanagment.DB.localdb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.Toast;

import com.task.square.black.taskmanagment.DB.Comment;
import com.task.square.black.taskmanagment.DB.CommentDao;
import com.task.square.black.taskmanagment.DB.DataBaseClient;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.TaskDao;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

public class TaskLocalDataSource implements TasksDataSource ,CommentDataSource {

    private static volatile TaskLocalDataSource INSTANCE;

    private TaskDao mTasksDao;
    private CommentDao mCommentDao;

    private Context mAppExecutors;

    // Prevent direct instantiation.
    private TaskLocalDataSource(@NonNull Context appExecutors,
                                @NonNull TaskDao tasksDao, CommentDao mCommentDao) {
        mAppExecutors = appExecutors;
        this.mTasksDao = tasksDao;
        this.mCommentDao = mCommentDao;
    }

    public static TaskLocalDataSource getInstance(@NonNull Context appExecutors,
                                                   @NonNull TaskDao tasksDao,CommentDao commentDao) {
        if (INSTANCE == null) {
            synchronized (TaskLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskLocalDataSource(appExecutors, tasksDao, commentDao);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Note: {@link LoadTasksCallback#onDataNotAvailable()} is fired if the database doesn't exist
     * or the table is empty.
     */
    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DataBaseClient
                        .getInstance(mAppExecutors)
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                callback.onTasksLoaded(tasks);

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }




    /**
     * Note: {@link GetTaskCallback#onDataNotAvailable()} is fired if the {@link Task} isn't
     * found.
     */
    @Override
    public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallback callback) {
        class GetTask extends AsyncTask<Void, Void,Task>{

            @Override
            protected Task doInBackground(Void... voids) {
                Task taskById = DataBaseClient
                        .getInstance(mAppExecutors)
                        .getAppDatabase()
                        .taskDao()
                        .getTaskById(taskId);
                return taskById;
            }

            @Override
            protected void onPostExecute(Task task) {
                super.onPostExecute(task);
                callback.onTaskLoaded(task);

            }
        }

        GetTask gt = new GetTask();
        gt.execute();

    }

    @Override
    public void saveTask(@NonNull final Task task) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DataBaseClient.getInstance(mAppExecutors).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mAppExecutors, "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();

    }

    @Override
    public void updateTask(@NonNull final Task task) {
        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DataBaseClient.getInstance(mAppExecutors).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mAppExecutors, "update"+task.getId(), Toast.LENGTH_LONG).show();
            }
        }
        UpdateTask st = new UpdateTask();
        st.execute();
    }
//    @Override
//    public void updateTaskToDone(@NonNull final Task task,i) {
//        class UpdateTask extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                //adding to database
//                DataBaseClient.getInstance(mAppExecutors).getAppDatabase()
//                        .taskDao()
//                        .update(task);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                Toast.makeText(mAppExecutors, "update"+task.getId(), Toast.LENGTH_LONG).show();
//            }
//        }
//        UpdateTask st = new UpdateTask();
//        st.execute();
//    }
    @Override
    public void completeTask(@NonNull String taskId) {
        // Not required for the local data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void activateTask(@NonNull final Task task) {
//        Runnable activateRunnable = new Runnable() {
//            @Override
//            public void run() {
//                mTasksDao.updateCompleted(task.getId(), false);
//            }
//        };
//        mAppExecutors.diskIO().execute(activateRunnable);
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        // Not required for the local data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void clearCompletedTasks() {

        //        Runnable clearTasksRunnable = new Runnable() {
//            @Override
//            public void run() {
//                mTasksDao.deleteCompletedTasks();
//
//            }
//        };
//
//        mAppExecutors.diskIO().execute(clearTasksRunnable);
    }

    @Override
    public void refreshTasks() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllTasks() {
        class DeleteAllTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DataBaseClient.getInstance(mAppExecutors).getAppDatabase()
                        .taskDao()
                        .deleteTasks();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mAppExecutors, "Saved", Toast.LENGTH_LONG).show();
            }
        }
        DeleteAllTask st = new DeleteAllTask();
        st.execute();
    }
    @Override
    public void deleteTask(@NonNull final String taskId) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DataBaseClient.getInstance(mAppExecutors).getAppDatabase()
                        .taskDao()
                        .deleteTaskById(taskId);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mAppExecutors, "Saved", Toast.LENGTH_LONG).show();
            }
        }
        DeleteTask st = new DeleteTask();
        st.execute();
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }

    @Override
    public void getCommentsByTaskID(@NonNull LoadCommentsCallback callback) {

    }

    @Override
    public void getCommentByTaskId(@NonNull final String taskId, @NonNull final GetCommentCallback callback) {
        class GetComments extends AsyncTask<Void, Void, List<Comment>> {

            @Override
            protected List<Comment> doInBackground(Void... voids) {
                List<Comment> comment = DataBaseClient
                        .getInstance(mAppExecutors)
                        .getAppDatabase()
                        .commentDao()
                        .getCommentsByTaskId(taskId);
                return comment;
            }

            @Override
            protected void onPostExecute(List<Comment> comments) {
                super.onPostExecute(comments);
                callback.onCommentsLoaded(comments);

            }
        }

        GetComments gt = new GetComments();
        gt.execute();
    }

    @Override
    public void saveComment(@NonNull final Comment comment) {
        class SaveComment extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DataBaseClient.getInstance(mAppExecutors).getAppDatabase()
                        .commentDao()
                        .insert(comment);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mAppExecutors, "Saved comment", Toast.LENGTH_LONG).show();
            }
        }
        SaveComment st = new SaveComment();
        st.execute();
    }

    @Override
    public void updateComment(@NonNull Comment comment) {

    }

    @Override
    public void deleteCommentsForTaskId(@NonNull String taskId) {

    }
}
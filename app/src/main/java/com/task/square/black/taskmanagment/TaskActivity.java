package com.task.square.black.taskmanagment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.task.square.black.taskmanagment.DB.DataBaseClient;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TaskRemoteFireBaseDataBase;
import com.task.square.black.taskmanagment.adapter.taskMVP.TaskPresenter;
import com.task.square.black.taskmanagment.adapter.taskMVP.TasksFragment;
import com.task.square.black.taskmanagment.util.ActivityUtil;

public class TaskActivity extends AppCompatActivity {
    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private Button mButton;

    private TaskPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);



        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = TasksFragment.newInstance();
            ActivityUtil.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }
        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab_add_task);

//        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.setTask("title");
                task.setDesc("description");
                task.setDate("jan 26");
                task.setIscompleted(false);

                mTasksPresenter.addTasktoDp(task);
            }
        });
        // Create the presenter
//        adding to database

        mTasksPresenter = new TaskPresenter(tasksFragment,TaskLocalDataSource.getInstance(
                getApplicationContext(),DataBaseClient.getInstance(getApplication()).getAppDatabase().taskDao()));
//        mTasksPresenter = new TaskPresenter(tasksFragment,TaskRemoteFireBaseDataBase.getInstance(), mTasksView);



    }




}

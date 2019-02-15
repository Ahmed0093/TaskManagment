package com.task.square.black.taskmanagment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.task.square.black.taskmanagment.DB.DataBaseClient;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TaskRemoteFireBaseDataBase;
import com.task.square.black.taskmanagment.adapter.taskMVP.TaskPresenter;
import com.task.square.black.taskmanagment.adapter.taskMVP.TasksFragment;
import com.task.square.black.taskmanagment.util.ActivityUtil;

public class TaskDetailsActivity extends AppCompatActivity {
    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private Button mButton;

    private TaskPresenter mTasksPresenter;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ImageView toolBarBackButton = findViewById(R.id.backarrow_toolbar);
        toolBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView deleteImage = findViewById(R.id.filter_img_toolbar);
        deleteImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_icons8_trash));
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTasksPresenter.deleteTask(task);
            }
        });

//        mTasksPresenter = new TaskPresenter(TaskDetailsActivity.class,TaskLocalDataSource.getInstance(
//                getApplicationContext(),DataBaseClient.getInstance(getApplication()).getAppDatabase().taskDao()));
//
//        mTasksPresenter.loadTasks();

    }


}

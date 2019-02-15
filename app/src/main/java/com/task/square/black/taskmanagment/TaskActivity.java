package com.task.square.black.taskmanagment;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

public class TaskActivity extends AppCompatActivity {
    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private Button mButton;

    private TaskPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
//
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle(getString(R.string.app_name));
////        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
//
//        Toolbar tb = (Toolbar) findViewById(R.id.toolbar_top);
//        setSupportActionBar(tb);
        ImageView toolBarBackButton = findViewById(R.id.backarrow_toolbar);
        toolBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView filterImage = findViewById(R.id.filter_img_toolbar);
        filterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTasksPresenter.filterDoneTasksOnly();
            }
        });
//        // Get the ActionBar here to configure the way it behaves.
//        final ActionBar ab = getSupportActionBar();
//        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
////        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
////        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
//

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
                task.setPriority("2");
                task.setIscompleted(false);

                Task task2 = new Task();
                task2.setTask("task2title");
                task2.setDesc("description2");
                task2.setDate("jan 25");
                task2.setPriority("3");
                task2.setIscompleted(true);

                mTasksPresenter.addTasktoDp(task2);
            }
        });
        // Create the presenter
//        adding to database

        mTasksPresenter = new TaskPresenter(tasksFragment,TaskLocalDataSource.getInstance(
                getApplicationContext(),DataBaseClient.getInstance(getApplication()).getAppDatabase().taskDao()));
//        mTasksPresenter = new TaskPresenter(tasksFragment,TaskRemoteFireBaseDataBase.getInstance(), mTasksView);

    mTasksPresenter.loadTasks();

    }
    public void alertCustomizedLayout(){

        AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);

        // get the layout inflater
        LayoutInflater inflater = TaskActivity.this.getLayoutInflater();

        // inflate and set the layout for the dialog
        // pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.task_addition, null))

                // action buttons
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // your sign in code here
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // remove the dialog from the screen
                    }
                })
                .show();

    }


}

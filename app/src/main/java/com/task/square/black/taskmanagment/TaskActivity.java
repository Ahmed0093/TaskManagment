package com.task.square.black.taskmanagment;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.task.square.black.taskmanagment.DB.DataBaseClient;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TaskRemoteFireBaseDataBase;
import com.task.square.black.taskmanagment.adapter.taskMVP.TaskPresenter;
import com.task.square.black.taskmanagment.adapter.taskMVP.TasksFragment;
import com.task.square.black.taskmanagment.util.ActivityUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TaskActivity extends AppCompatActivity {
    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private Button mButton;
    TextView userNameTitle;
    AlertDialog dialog;
    private TaskPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        String username= getIntent().getStringExtra("user");

        userNameTitle = findViewById(R.id.toolbar_tv);
        userNameTitle.setText(username+"'s" + getString(R.string.tasks_text));
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TaskActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.task_addition,null);
                final EditText taskName = (EditText) mView.findViewById(R.id.taskname);
                Button mAdd = (Button) mView.findViewById(R.id.addtaskbtn);
                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!taskName.getText().toString().isEmpty()) {

                            String result = taskName.getText().toString();


                            Task task2 = getTaskbyName(result);
                            mTasksPresenter.addTasktoDp(task2);
                            dialog.dismiss();

                        }
                        else {
                            Toast.makeText(TaskActivity.this, "Error pls Write", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
                mBuilder.setView(mView);
                mBuilder.setTitle(getString(R.string.add_task_title));
                 dialog = mBuilder.create();
                dialog.show();


            }
        });
        // Create the presenter
//        adding to database

        mTasksPresenter = new TaskPresenter(tasksFragment,TaskLocalDataSource.getInstance(
                getApplicationContext()
                ,DataBaseClient.getInstance(getApplication()).getAppDatabase().taskDao()
                , DataBaseClient.getInstance(getApplication()).getAppDatabase().commentDao()));

    mTasksPresenter.loadTasks();

    }

    @NonNull
    private Task getTaskbyName(String result) {
        Task task2 = new Task();
        task2.setTask(result);
        task2.setPriority("0");
        task2.setIscompleted(false);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        task2.setDesc(formatter.format(new Date()));
        String newDate = "2010/10/24 15:55:56";
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = format.parse(newDate);
            task2.setDate(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return task2;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}

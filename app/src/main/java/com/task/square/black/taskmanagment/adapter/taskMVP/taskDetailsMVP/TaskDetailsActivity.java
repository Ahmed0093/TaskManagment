package com.task.square.black.taskmanagment.adapter.taskMVP.taskDetailsMVP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.design.widget.CheckableImageButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.task.square.black.taskmanagment.DB.Comment;
import com.task.square.black.taskmanagment.DB.DataBaseClient;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.localdb.TaskLocalDataSource;
import com.task.square.black.taskmanagment.DB.localdb.TaskRemoteFireBaseDataBase;
import com.task.square.black.taskmanagment.R;
import com.task.square.black.taskmanagment.adapter.TaskAdapter;
import com.task.square.black.taskmanagment.adapter.TaskDetailsAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TaskDetailsActivity extends AppCompatActivity implements TaskDetailsContract.View {
    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private Button mButton;
    private RecyclerView recyclerView;
    private TaskDetailsAdapter taskDetailsAdapter;

    private TaskDetailsContract.Presenter taskDetailsPresenter;
    private Task task;
    CheckableImageButton priorityimage1;
    CheckableImageButton priorityimage2;
    CheckableImageButton priorityimage3;
    EditText commentEdittext;
    private String TASK_TAG = "task_tag";
    private TextView userNameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To retrieve object in second Activity
        setContentView(R.layout.activity_details_task);
        task = (Task) getIntent().getSerializableExtra(TASK_TAG);
        recyclerView = findViewById(R.id.recyclerview_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ImageView toolBarBackButton = findViewById(R.id.backarrow_toolbar);
        toolBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView deleteToolbarText = findViewById(R.id.toolbar_img_tv);
        deleteToolbarText.setVisibility(View.GONE);
        userNameTitle = findViewById(R.id.toolbar_tv);
        userNameTitle.setText(task.getTask());
//fab_add_task2
        ImageView deleteImage = findViewById(R.id.filter_img_toolbar);
        deleteImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_icons8_trash));
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDetailsPresenter.deleteTask(task);
            }
        });
        commentEdittext = findViewById(R.id.chat_edittext);
        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab_add_task2);

//        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Comment comment = new Comment();
                if(commentEdittext.getText() != null ){
                    comment.setCommentdesc(commentEdittext.getText().toString());
                }
                comment.setTaskid(task.getId());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                comment.setAgodate(formatter.format(new Date()));
//                System.out.println(forchat_edittextmatter.print(dateTime));
                taskDetailsPresenter.saveTask(task.getId(),comment);
                taskDetailsPresenter.updateDetailsActivityUi(task.getId());

            }
        });

        taskDetailsPresenter = new TaskDetailsPresenter(this, TaskLocalDataSource.getInstance(
                getApplicationContext(), DataBaseClient.getInstance(getApplication()).getAppDatabase().taskDao(), DataBaseClient.getInstance(getApplication()).getAppDatabase().commentDao()));
        initTaskUi();
        taskDetailsPresenter.updateDetailsActivityUi(task.getId());

    }

    private void initTaskUi() {
        TextView dateValue = findViewById(R.id.date_value);
        final ImageView doneImage = findViewById(R.id.done_image);
        priorityimage1 = findViewById(R.id.priorityImage1);
        priorityimage2 = findViewById(R.id.priorityImage2);
        priorityimage3 = findViewById(R.id.priorityImage3);
        dateValue.setText(task.getDesc());
        setDeafultIndicatorImages();
        setPrioityImageClickListener();
        setDoneImage(doneImage);
        doneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(task.isIscompleted()) // Change Status
                {
                    task.setIscompleted(false);
                } else {
                    task.setIscompleted(true);

                }
                setDoneImage(doneImage);
                taskDetailsPresenter.updateTaskinDatabase(task);




            }
        });    }

    private void setDoneImage(ImageView doneImage) {
        if (task.isIscompleted()) {
            doneImage.setImageDrawable(getDrawable(R.drawable.ic_check_circle_black_24dp));

        } else {
            doneImage.setImageDrawable(getDrawable(R.drawable.ic_panorama_fish_eye_black_24dp));
        }
    }

    private void setPrioityImageClickListener() {
        priorityimage1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View button) {
                button.setSelected(!button.isSelected());

                if (button.isSelected()) {
                    priorityimage2.setSelected(false);
                    priorityimage3.setSelected(false);
                    task.setPriority("1"); //Handle selected state change
                } else {
                    //Handle de-select state change
                }

            }
        });
        priorityimage2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View button) {
                button.setSelected(!button.isSelected());

                if (button.isSelected()) {
                    priorityimage1.setSelected(false);
                    priorityimage3.setSelected(false);
                    task.setPriority("2"); //Handle selected state change
                    taskDetailsPresenter.updateTaskinDatabase(task);
                } else {
                    //Handle de-select state change
                }

            }
        });
        priorityimage3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View button) {
                button.setSelected(!button.isSelected());

                if (button.isSelected()) {
                    priorityimage1.setSelected(false);
                    priorityimage2.setSelected(false);
                    task.setPriority("3"); //Handle selected state change
                    taskDetailsPresenter.updateTaskinDatabase(task);
                } else {
                    //Handle de-select state change
                }

            }
        });
    }

    private void setDeafultIndicatorImages() {
        priorityimage1.setImageDrawable(getDrawable(R.drawable.priority1image));
        priorityimage2.setImageDrawable(getDrawable(R.drawable.priority2image));
        priorityimage3.setImageDrawable(getDrawable(R.drawable.priority3image));
        setIndicatorBasedonPriority();
    }

    private void setIndicatorBasedonPriority() {
        if (task.getPriority() == null) {
//TODO hide priority field
        }
        switch (task.getPriority()) {
            case "1":
                priorityimage1.setSelected(true);

                break;
            case "2":
                priorityimage2.setSelected(true);
                break;
            case "3":
                priorityimage1.setSelected(true);
                break;
            default:
        }
    }



    @Override
    public void setPresenter(TaskDetailsContract.Presenter presenter) {
        taskDetailsPresenter = presenter;
    }

    @Override
    public void showTaskWithComment(List<Task> tasks) {

    }

    @Override
    public void showTaskDetailsUi(String taskId) {

    }

    @Override
    public void showCompletedTasksCleared() {

    }

    @Override
    public void showActiveFilterLabel() {

    }

    @Override
    public void showCompletedFilterLabel() {

    }

    @Override
    public void showAllFilterLabel() {

    }

    @Override
    public void showComments(List<Comment> comments) {
        taskDetailsAdapter = new TaskDetailsAdapter(getApplicationContext(), comments, task, getResources());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskDetailsAdapter);
        taskDetailsAdapter.notifyDataSetChanged();
        commentEdittext.setText("");
        hideKeyboard();

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showNoComments() {

    }

    @Override
    public void updateUi() {
    }

    @Override
    public void backToTasksListAsTaskDeletedFromdetails() {
        finish();
    }
}

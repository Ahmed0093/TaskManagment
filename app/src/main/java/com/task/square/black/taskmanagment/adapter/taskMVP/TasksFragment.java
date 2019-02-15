package com.task.square.black.taskmanagment.adapter.taskMVP;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.R;
import com.task.square.black.taskmanagment.adapter.AdapterClickListener;
import com.task.square.black.taskmanagment.adapter.TaskAdapter;

import java.util.List;

public class TasksFragment extends Fragment implements TasksContract.View,AdapterClickListener {
    private TasksContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private SearchView searchView;

    public TasksFragment() {
        // Requires empty public constructor
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.task_frag, container, false);

         recyclerView = root.findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTasks(List<Task> tasks) {
         adapter = new TaskAdapter(getContext(), tasks, this, getResources());
                recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showAddTask() {

    }

    @Override
    public void showTaskDetailsUi(String taskId) {

    }

    @Override
    public void showTaskMarkedComplete() {

    }

    @Override
    public void showTaskMarkedActive() {

    }

    @Override
    public void showCompletedTasksCleared() {

    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showNoTasks() {

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
    public void showNoActiveTasks() {

    }

    @Override
    public void showNoCompletedTasks() {

    }

    @Override
    public void showSuccessfullySavedMessage() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showFilteringPopUpMenu() {

    }

    @Override
    public void OpenTaskViewDetailsWithCommentList(Task taskCLicked) {
        //TODO NAVIGATE TO TASK WITH COMMENT VIEW..
        Toast.makeText(getContext(), "titleClicked", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(Task taskCLicked) {
//        if(taskCLicked.isLongClickedPressed()) {
//            mPresenter.deleteTask(taskCLicked);
//
//        }
//        mPresenter.changeTaskStatus(taskCLicked);
 }

    @Override
    public void onStatusClicked(Task taskCLicked) {
        mPresenter.changeTaskStatus(taskCLicked);
    }


    @Override
    public void onTitleClicked(Task taskCLicked) {
        mPresenter.navigateToTaskView(taskCLicked);
    }

    @Override
    public void onLongClickPresed(Task taskClicked) {
        mPresenter.updateAdapterUI(taskClicked);
    }

    @Override
    public void onDeleteTask(Task taskClicked) {
        mPresenter.deleteTask(taskClicked);
    }

    @Override
    public void updateDatabase(Task taskClicked) {
        mPresenter.updateTaskinDatabase(taskClicked);
    }


}

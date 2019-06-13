package com.task.square.black.taskmanagment.adapter.taskMVP;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.R;
import com.task.square.black.taskmanagment.adapter.AdapterClickListener;
import com.task.square.black.taskmanagment.adapter.TaskAdapter;
import com.task.square.black.taskmanagment.adapter.taskMVP.taskDetailsMVP.TaskDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment implements TasksContract.View,AdapterClickListener {
    private static final String TASK_TAG = "task_tag";
    private TasksContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TaskAdapter(getContext(), new ArrayList<Task>(), new ArrayList<Task>(), this, getResources());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
          //  mPresenter.loadTasks();
        }
    }

    @Override
    public void showTasks(List<Task> tasks) {
        adapter.setFilteredList(false);
        adapter.setTaskList(tasks);
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
        Intent intent = new Intent(getActivity(),TaskDetailsActivity.class);
        intent.putExtra(TASK_TAG,taskCLicked);
        startActivity(intent);
        Toast.makeText(getContext(), "titleClicked", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showTasksWhenItemDeletedWithPosition(List<Task> tasks, int adapterPosition) {
        if (adapter.isFilteredList()) {
            mPresenter.filterDoneTasksOnly();
        } else {
            adapter.setTaskList(tasks);
            adapter.notifyItemRemoved(adapterPosition);

        }
    }

    @Override
    public void notifyTaskInserted(List<Task> tasks) {
        adapter.setTaskList(tasks);
        adapter.notifyItemInserted(adapter.getItemCount());
    }

    @Override
    public void updateTaskItem(List<Task> tasks, int adapterPosition) {
        adapter.setTaskList(tasks);
        adapter.notifyItemChanged(adapterPosition);
    }

    @Override
    public void showFilteredDoneTasks(List<Task> result) {
        adapter.setFilteredTaskList(result);
        adapter.setFilteredList(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Task taskCLicked) {
    //No implementaton required
 }

    @Override
    public void onStatusClicked(Task taskCLicked, int adapterPosition) {
        mPresenter.changeTaskStatus(taskCLicked,adapterPosition);
    }


    @Override
    public void onTitleClicked(Task taskCLicked) {
        mPresenter.navigateToTaskView(taskCLicked);
    }

    @Override
    public void onLongClickPresed(Task taskClicked, int adapterPosition) {
        mPresenter.updateAdapterUI(taskClicked,adapterPosition);
    }

    @Override
    public void onDeleteTask(Task taskClicked, int adapterPosition) {
        mPresenter.deleteTask(taskClicked,adapterPosition);
    }

    @Override
    public void updateDatabase(Task taskClicked, int adapterPosition) {
        mPresenter.updateTaskinDatabase(taskClicked,adapterPosition);
    }


}

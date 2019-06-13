package com.task.square.black.taskmanagment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import com.google.android.material.internal.CheckableImageButton;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> {

    private Context mCtx;

    public boolean isFilteredList() {
        return isFilteredList;
    }

    public void setFilteredList(boolean filteredList) {
        isFilteredList = filteredList;
    }

    private boolean isFilteredList = false;

    public List<Task> getTaskListitems() {
        return taskListitems;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskListitems = taskList;
    }

    public void setFilteredTaskList(List<Task> filteredTaskList) {
        this.filteredTaskList = filteredTaskList;
    }

    private List<Task> taskListitems;
    private List<Task> filteredTaskList;

    private AdapterClickListener adapteritemCLickListener;
    private Resources resources;

    public TaskAdapter(Context mCtx, List<Task> taskList, List<Task> filteredTaskList, AdapterClickListener adapteritemCLickListener, Resources resources) {
        this.mCtx = mCtx;
        this.taskListitems = taskList;
        this.filteredTaskList = filteredTaskList;
        this.adapteritemCLickListener = adapteritemCLickListener;
        this.resources = resources;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new TasksViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "RestrictedApi"})
    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task t;
        if (!isFilteredList) {
            t = taskListitems.get(position);
        } else {
            t = filteredTaskList.get(position);
        }
        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesc());


        if (t.isIscompleted()) {
            holder.imageViewStatus.setSelected(true);

        } else {
            holder.imageViewStatus.setSelected(false);
        }
        if (t.getPriority() == null) {
            t.setPriority("0"); //TODO to be
        }
        switch (t.getPriority()) {
            case "0":
                holder.imageViewPriority1.setSelected(false);
                holder.imageViewPriority2.setSelected(false);
                holder.imageViewPriority3.setSelected(false);


                break;
            case "1":
                holder.imageViewPriority1.setSelected(true);
                holder.imageViewPriority2.setSelected(false);
                holder.imageViewPriority3.setSelected(false);


                break;
            case "2":
                holder.imageViewPriority1.setSelected(false);
                holder.imageViewPriority2.setSelected(true);
                holder.imageViewPriority3.setSelected(false);
                break;
            case "3":
                holder.imageViewPriority1.setSelected(false);
                holder.imageViewPriority2.setSelected(false);
                holder.imageViewPriority3.setSelected(true);
                break;
            default:
        }
        if (t.isLongClickedPressed()) {
            holder.imageViewdDeletek.setImageDrawable(resources.getDrawable(R.drawable.ic_icons8_trash));
            holder.imageViewdDeletek.setVisibility(View.VISIBLE);
            holder.prioritylayout.setVisibility(View.GONE);

        } else {
            holder.imageViewdDeletek.setVisibility(View.GONE);
            holder.prioritylayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (!isFilteredList) {
            return taskListitems.size();
        } else {
            return filteredTaskList.size();
        }
    }

    public void setUpdatedTaskList(List<Task> tasks, int adapterPosition) {
        setTaskList(tasks);
        notifyItemRemoved(adapterPosition);

    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final CheckableImageButton imageViewPriority1, imageViewPriority2, imageViewPriority3, imageViewStatus;
        private final LinearLayout prioritylayout;
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;
        ImageView imageViewdDeletek;
        RelativeLayout tasklayoutView;

        public TasksViewHolder(View itemView) {
            super(itemView);
            tasklayoutView = itemView.findViewById(R.id.task_container);
            imageViewStatus = itemView.findViewById(R.id.complete);
            textViewTask = itemView.findViewById(R.id.title);
            textViewDesc = itemView.findViewById(R.id.desc);
            imageViewPriority1 = itemView.findViewById(R.id.priorityImage1);
            imageViewPriority2 = itemView.findViewById(R.id.priorityImage2);
            imageViewPriority3 = itemView.findViewById(R.id.priorityImage3);
            imageViewdDeletek = itemView.findViewById(R.id.deleteimage);

            prioritylayout = itemView.findViewById(R.id.prioritylayout);
            initViewsClickListeners();
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private void initViewsClickListeners() {
            imageViewdDeletek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                    if (taskClicked.isLongClickedPressed()) {
                        adapteritemCLickListener.onDeleteTask(taskClicked, getAdapterPosition());
                    }

                }
            });
            imageViewPriority1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View button) {
                    button.setSelected(!button.isSelected());

                    if (button.isSelected()) {
                        imageViewPriority2.setSelected(false);
                        imageViewPriority3.setSelected(false);
                        Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                        taskClicked.setPriority("1"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    } else {
                        //Handle de-select state change
                        imageViewPriority2.setSelected(false);
                        imageViewPriority3.setSelected(false);
                        Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                        taskClicked.setPriority("0"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    }

                }
            });
            imageViewPriority2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View button) {
                    button.setSelected(!button.isSelected());

                    if (button.isSelected()) {
                        imageViewPriority1.setSelected(false);
                        imageViewPriority3.setSelected(false);
                        Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                        taskClicked.setPriority("2"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    } else {
                        //Handle de-select state change
                        imageViewPriority1.setSelected(false);
                        imageViewPriority3.setSelected(false);
                        Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                        taskClicked.setPriority("0"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    }

                }
            });
            imageViewPriority3.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View button) {
                    button.setSelected(!button.isSelected());

                    if (button.isSelected()) {
                        imageViewPriority1.setSelected(false);
                        imageViewPriority2.setSelected(false);
                        Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                        taskClicked.setPriority("3"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    } else {
                        //Handle de-select state change
                        imageViewPriority1.setSelected(false);
                        imageViewPriority2.setSelected(false);
                        Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                        taskClicked.setPriority("0"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    }

                }
            });
            imageViewStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    button.setSelected(!button.isSelected());
                    Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());

                    if (button.isSelected()) {
                        taskClicked.setIscompleted(true);

                    } else {
                        //Handle de-select state change
                        taskClicked.setIscompleted(false);

                    }
                    adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());

                }

            });
            textViewTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
                    adapteritemCLickListener.onTitleClicked(taskClicked);

                }
            });
        }

        @Override
        public void onClick(View view) {
            // no implementation required
        }

        @Override
        public boolean onLongClick(View view) {
            Task taskClicked = getTaskClickedWIthPosition(getAdapterPosition());
            adapteritemCLickListener.onLongClickPresed(taskClicked, getAdapterPosition());
            return false;
        }


        private Task getTaskClickedWIthPosition(int position) {
            Task taskClicked;
            if (!isFilteredList) {
                taskClicked = taskListitems.get(position);
            } else {
                taskClicked = filteredTaskList.get(position);
            }
            return taskClicked;
        }
    }
}
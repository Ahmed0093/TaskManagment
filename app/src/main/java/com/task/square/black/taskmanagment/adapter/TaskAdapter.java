package com.task.square.black.taskmanagment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.widget.RecyclerView;
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

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private List<Task> taskList;
    private AdapterClickListener adapteritemCLickListener;
    private Resources resources;

    public TaskAdapter(Context mCtx, List<Task> taskList, AdapterClickListener adapteritemCLickListener, Resources resources) {
        this.mCtx = mCtx;
        this.taskList = taskList;
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
        Task t = taskList.get(position);
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
        return taskList.size();
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
            tasklayoutView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Task taskClicked = taskList.get(getAdapterPosition());
                    adapteritemCLickListener.onLongClickPresed(taskClicked, getAdapterPosition());
                    return false;
                }
            });
            imageViewdDeletek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task taskClicked = taskList.get(getAdapterPosition());
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
                        Task taskClicked = taskList.get(getAdapterPosition());
                        taskClicked.setPriority("1"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    } else {
                        //Handle de-select state change
                        imageViewPriority2.setSelected(false);
                        imageViewPriority3.setSelected(false);
                        Task taskClicked = taskList.get(getAdapterPosition());
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
                        Task taskClicked = taskList.get(getAdapterPosition());
                        taskClicked.setPriority("2"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    } else {
                        //Handle de-select state change
                        imageViewPriority1.setSelected(false);
                        imageViewPriority3.setSelected(false);
                        Task taskClicked = taskList.get(getAdapterPosition());
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
                        Task taskClicked = taskList.get(getAdapterPosition());
                        taskClicked.setPriority("3"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    } else {
                        //Handle de-select state change
                        imageViewPriority1.setSelected(false);
                        imageViewPriority2.setSelected(false);
                        Task taskClicked = taskList.get(getAdapterPosition());
                        taskClicked.setPriority("0"); //Handle selected state change
                        adapteritemCLickListener.updateDatabase(taskClicked, getAdapterPosition());
                    }

                }
            });
            imageViewStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    button.setSelected(!button.isSelected());
                    Task taskClicked = taskList.get(getAdapterPosition());

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
                    Task taskClicked = taskList.get(getAdapterPosition());
                    adapteritemCLickListener.onTitleClicked(taskClicked);

                }
            });
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // no implementation required
        }

        @Override
        public boolean onLongClick(View view) {
            Task taskClicked = taskList.get(getAdapterPosition());
            adapteritemCLickListener.onLongClickPresed(taskClicked, getAdapterPosition());
            return false;
        }
    }
}
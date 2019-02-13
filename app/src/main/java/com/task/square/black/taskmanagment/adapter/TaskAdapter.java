package com.task.square.black.taskmanagment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> {

    private Context mCtx;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesc());
//        holder.textViewFinishBy.setText(t.getDate());


        if (t.isIscompleted()) {
            holder.imageViewStatus.setImageDrawable(resources.getDrawable(R.drawable.ic_panorama_fish_eye_black_24dp));


        } else {
            holder.imageViewStatus.setImageDrawable(resources.getDrawable(R.drawable.ic_check_circle_black_24dp));
        }
        if (t.isLongClickedPressed()) {
            holder.imageViewPriority.setImageDrawable(resources.getDrawable(R.drawable.ic_icons8_trash));
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener {

        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;
        ImageView imageViewStatus,imageViewPriority;

        public TasksViewHolder(View itemView) {
            super(itemView);

            imageViewStatus = itemView.findViewById(R.id.complete);
            textViewTask = itemView.findViewById(R.id.title);
            textViewDesc = itemView.findViewById(R.id.desc);
            imageViewPriority = itemView.findViewById(R.id.priorityImage);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task taskClicked = taskList.get(getAdapterPosition());
            adapteritemCLickListener.onItemClick(taskClicked);
        }

        @Override
        public boolean onLongClick(View view) {
            Task taskClicked = taskList.get(getAdapterPosition());
            adapteritemCLickListener.onLongClickPresed(taskClicked);
            return false;
        }
    }
}
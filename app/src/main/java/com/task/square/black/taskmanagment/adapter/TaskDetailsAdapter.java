package com.task.square.black.taskmanagment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.square.black.taskmanagment.DB.Comment;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskDetailsAdapter extends RecyclerView.Adapter<TaskDetailsAdapter.TasksViewHolder>  {
    private Context mCtx;
    private List<Comment> commentList;
    private Task detailedTask;
    private AdapterClickListener adapteritemCLickListener;
    private Resources resources;

    public TaskDetailsAdapter(Context mCtx, List<Comment> commentList, Task detailedTask, Resources resources) {
        this.mCtx = mCtx;
        this.commentList = commentList;
        this.detailedTask = detailedTask;
        this.resources = resources;
    }

    @Override
    public TaskDetailsAdapter.TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_comment, parent, false);
        return new TaskDetailsAdapter.TasksViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "RestrictedApi"})
    @Override
    public void onBindViewHolder(TaskDetailsAdapter.TasksViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.textViewTaskComment.setText(comment.getCommentdesc());
        holder.textViewDateAgo.setText(covertTimeToText(comment.getAgodate()));



    }



    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView  textViewTaskComment, textViewDateAgo;
        RelativeLayout tasklayoutView;
        public TasksViewHolder(View itemView) {
            super(itemView);
            textViewTaskComment = itemView.findViewById(R.id.comment_description);
            textViewDateAgo = itemView.findViewById(R.id.comment_time_ago);

        }


    }

    public String covertTimeToText(String dataDate) {

        String convTime = null;

        String prefix = "";
        String suffix = "Ago";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date pasTime = dateFormat.parse(dataDate);

            Date nowTime = new Date();

            long dateDiff = nowTime.getTime() - pasTime.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                convTime = second+" Seconds "+suffix;
            } else if (minute < 60) {
                convTime = minute+" Minutes "+suffix;
            } else if (hour < 24) {
                convTime = hour+" Hours "+suffix;
            } else if (day >= 7) {
                if (day > 30) {
                    convTime = (day / 30)+" Months "+suffix;
                } else if (day > 360) {
                    convTime = (day / 360)+" Years "+suffix;
                } else {
                    convTime = (day / 7) + " Week "+suffix;
                }
            } else if (day < 7) {
                convTime = day+" Days "+suffix;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ConvTimeE", e.getMessage());
        }

        return convTime;

    }
}

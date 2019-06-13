package com.task.square.black.taskmanagment.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "taskid")
    private int taskid;

    @ColumnInfo(name = "commentdesc")
    private String commentdesc;

    @ColumnInfo(name = "agodate")
    private String agodate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getCommentdesc() {
        return commentdesc;
    }

    public void setCommentdesc(String commentdesc) {
        this.commentdesc = commentdesc;
    }

    public String getAgodate() {
        return agodate;
    }

    public void setAgodate(String agodate) {
        this.agodate = agodate;
    }
}

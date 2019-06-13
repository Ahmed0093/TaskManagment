package com.task.square.black.taskmanagment.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "priority")
    private String priority;

    @ColumnInfo(name = "iscompleted")
    private boolean iscompleted;

    @ColumnInfo(name = "islongclicked")
    private boolean isLongClickedPressed = false;



    /*
     * Getters and Setters
     * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isIscompleted() {
        return iscompleted;
    }

    public boolean isLongClickedPressed() {
        return isLongClickedPressed;
    }

    public void setLongClickedPressed(boolean longClickedPressed) {
        isLongClickedPressed = longClickedPressed;
    }

    public void setIscompleted(boolean iscompleted) {
        this.iscompleted = iscompleted;
    }
    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("taskid", id);
//        result.put("author", author);
        result.put("tasktitle", task);
        result.put("taskbody", desc);
        result.put("starDate", date);
//        result.put("stars", stars);

        return result;
    }
    // [END post_to_map]

}
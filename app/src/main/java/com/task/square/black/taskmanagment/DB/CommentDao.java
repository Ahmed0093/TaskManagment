package com.task.square.black.taskmanagment.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CommentDao {
    @Query("SELECT * FROM comment")
    List<Comment> getAll();

    @Insert
    void insert(Comment comment);

    @Delete
    void delete(Comment comment);

    @Update
    void update(Comment comment);

    /**
     * Select a task by id.
     *
     * @param taskId the task id.
     * @return the task with taskId.
     */
    @Query("SELECT * FROM Comment WHERE taskid = :taskId")
    List<Comment>   getCommentsByTaskId(String taskId);

    /**
     * Delete a task by id.
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM Comment WHERE taskid = :taskId")
    int deleteCommentswithTaskId(String taskId);

//
//    @Query("DELETE FROM Comment WHERE id = :taskId")
//    int deleteAllCommentswithTaskId(String taskId);
    /**
     * Delete all tasks.
     */
    @Query("DELETE FROM Comment")
    void deleteAllComments();
}

package com.task.square.black.taskmanagment.DB.localdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.DB.TaskDao;

import java.util.HashMap;
import java.util.Map;

public class TaskRemoteFireBaseDataBase  implements TasksDataSource {

    private static volatile DatabaseReference INSTANCE;
    private DatabaseReference mPostReference;
    private ValueEventListener mPostListener;
//    private final TaskDao mTasksDao;
//    private final Context mAppExecutors;
//
//    FirebaseDatabase firebaseDatabase;
    // Prevent direct instantiation.
//    private TaskRemoteFireBaseDataBase(@NonNull Context appExecutors,
//                                @NonNull TaskDao tasksDao) {
//        mAppExecutors = appExecutors;
//        mTasksDao = tasksDao;
//    }

    public static DatabaseReference getInstance(Context applicationContext, TaskDao taskDao) {
        if (INSTANCE == null) {
            synchronized (TaskLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = FirebaseDatabase.getInstance().getReference();
                    ;
                }
            }
        }
        return INSTANCE;

    }
    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        // Initialize Database
//        mPostReference = FirebaseDatabase.getInstance().getReference()
//                .child("posts").child(mPostKey);
//
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Task task = dataSnapshot.getValue(Task.class);
//                // [START_EXCLUDE]
//                mAuthorView.setText(post.author);
//                mTitleView.setText(post.title);
//                mBodyView.setText(post.body);
//                // [END_EXCLUDE]
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
//                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        };
//        mPostReference.addValueEventListener(postListener);
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {
//// Create new post at /user-posts/$userid/$postid and at
//            // /posts/$postid simultaneously
       String userId = "user1";//FirebaseAuth.getInstance().getCurrentUser().getUid();
//            String key = INSTANCE.child("tasks").push().getKey();
////            Task post = new Post(userId, username, title, body);
//            Map<String, Object> postValues = task.toMap();
//
//            Map<String, Object> childUpdates = new HashMap<>();
//            childUpdates.put("/posts/" + key, postValues);
//            childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
//
//            INSTANCE.updateChildren(childUpdates);
        INSTANCE.child("users").child(userId).setValue(task);

    }

    @Override
    public void updateTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}

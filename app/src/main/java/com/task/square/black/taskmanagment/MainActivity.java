package com.task.square.black.taskmanagment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.task.square.black.taskmanagment.DB.DataBaseClient;
import com.task.square.black.taskmanagment.DB.Task;
import com.task.square.black.taskmanagment.adapter.TaskAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView recyclerView;
    private Button buttonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        saveTask();
//        recyclerView = findViewById(R.id.recyclerview_tasks);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.login_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });


        getTasks();

    }

    private void getTasks() {
    }


    private void saveTask() {

    }

    //   private void getTasks() {
//        class GetTasks extends AsyncTask<Void, Void, List<Task>> {
//
//            @Override
//            protected List<Task> doInBackground(Void... voids) {
//                List<Task> taskList = DataBaseClient
//                        .getInstance(getApplicationContext())
//                        .getAppDatabase()
//                        .taskDao()
//                        .getAll();
//                return taskList;
//            }
//
//            @Override
//            protected void onPostExecute(List<Task> tasks) {
//                super.onPostExecute(tasks);
//                TaskAdapter adapter = new TaskAdapter(MainActivity.this, tasks);
//                recyclerView.setAdapter(adapter);
//            }
//        }
//
//        GetTasks gt = new GetTasks();
//        gt.execute();
//    }
//    void saveTask(){
//
//    class SaveTask extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            //creating a task
//            Task task = new Task();
//            task.setTask("title");
//            task.setDesc("description");
//            task.setDate("jan 26");
//            task.setIscompleted(false);
//
//            //adding to database
//            DataBaseClient.getInstance(getApplicationContext()).getAppDatabase()
//                    .taskDao()
//                    .insert(task);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
////            finish();
////            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
//        }
//    }
//        SaveTask st = new SaveTask();
//        st.execute();
//
//}
   // }
}


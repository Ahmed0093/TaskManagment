package com.task.square.black.taskmanagment.DB;

import androidx.room.Room;
import android.content.Context;

public class DataBaseClient {

    private Context mCtx;
    private static DataBaseClient mInstance;

    //our app database object
    private AppDataBase appDatabase;

    private DataBaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyTasks is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDataBase.class, "MyTasks")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DataBaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DataBaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDataBase getAppDatabase() {
        return appDatabase;
    }

}

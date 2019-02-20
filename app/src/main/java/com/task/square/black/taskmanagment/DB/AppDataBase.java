package com.task.square.black.taskmanagment.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

@Database(entities = {Task.class,Comment.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract CommentDao commentDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE task"
                    + " ADD COLUMN islongclicked INTEGER");
        }
    };
}

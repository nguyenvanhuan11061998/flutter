package com.example.project1.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project1.inputStudent.model.InforStudent;

@Database(entities = {InforStudent.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    private static final String STUDENT_MANAGER = "student_manager";
    private static StudentDatabase database = null;

    public static StudentDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context
                    , StudentDatabase.class
                    , STUDENT_MANAGER)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public abstract StudentDao getStudentDao();
}

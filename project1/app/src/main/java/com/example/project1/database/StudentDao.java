package com.example.project1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project1.inputStudent.model.InforStudent;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student")
    List<InforStudent> getAllStudent();

    @Insert(onConflict = REPLACE)
    void insert(InforStudent... students);

    @Update
    void update(InforStudent... students);

    @Delete
    void delete(InforStudent student);
}
